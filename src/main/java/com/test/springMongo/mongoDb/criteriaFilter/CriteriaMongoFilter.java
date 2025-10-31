package com.test.springMongo.mongoDb.criteriaFilter;

import com.test.springMongo.utils.DateUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CriteriaMongoFilter {


    public static Query queryBuilder(String filter) {
        Query query = new Query();
        try {
            checkFilterValidity(filter);
            query.addCriteria(splitFilter(filter));
        } catch (Exception e) {
            System.out.println("criteria fail, " + e);
            // logger
            return null;
        }
        return query;
    }

    public static Criteria criteriaOperatorBuilder(Map<String, List<Criteria>> mapper) {
        Criteria criteria = new Criteria();
        try {
            ///// isolation group case /////
            if (mapper.get("andList").size() != 0)
                criteria = criteria.andOperator(mapper.get("andList").stream()
                        .distinct()
                        .collect(Collectors.toList()));
            if (mapper.get("orList").size() != 0)
                criteria = criteria.orOperator(mapper.get("orList").stream()
                        .distinct()
                        .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new RuntimeException("Criteria builder pattern exception ! {" + criteria + "} \n");
        }
        return criteria;
    }


    public static Criteria splitFilter(String filter) throws Exception {
        Criteria criteria = null;
        String globalNextOpe = null;
        List<Criteria> listAndOperator = new LinkedList<>();
        List<Criteria> listOrOperator = new LinkedList<>();

        Map<String, List<Criteria>> allParms = new HashMap<>();

        Pattern patternSubGroup = Pattern.compile("(\\sAND\\s|\\sOR\\s)?((\\((?:[^)(]|\\(*\\))*\\))(\\sAND\\s|\\sOR\\s)?)", Pattern.CASE_INSENSITIVE);
        Matcher matcherSubGroup = patternSubGroup.matcher(filter);

        while (matcherSubGroup.find()) {

            String expresWithPreviousOp = matcherSubGroup.group(0);
            String previousOpe = matcherSubGroup.group(1);
            String subExpression = matcherSubGroup.group(3);
            String nextOpe = matcherSubGroup.group(4);

            filter = filter.replace(expresWithPreviousOp, "");

            String subExpressionContent = subExpression.replaceFirst("\\(", "").replaceAll("\\)$", "");

            if (globalNextOpe != null) {
                if (globalNextOpe.equals("AND")) {
                    listAndOperator.add(splitFilter(subExpressionContent));
                } else if (globalNextOpe.equals("OR")) {
                    listOrOperator.add(splitFilter(subExpressionContent));
                }
            } else if (previousOpe != null) {
                if (previousOpe.trim().equals("AND")) {
                    globalNextOpe = "AND";
                    listAndOperator.add(splitFilter(subExpressionContent));
                } else if (previousOpe.trim().equals("OR")) {
                    globalNextOpe = "OR";
                    listOrOperator.add(splitFilter(subExpressionContent));
                }
            }

            //////////////////////
            if (nextOpe != null) {
                if (nextOpe.trim().equals("AND")) {
                    globalNextOpe = "AND";
                    listAndOperator.add(splitFilter(subExpressionContent));
                } else if (nextOpe.trim().equals("OR")) {
                    globalNextOpe = "OR";
                    listOrOperator.add(splitFilter(subExpressionContent));
                }
            }

            // cas unique (toto = tata)
            if (nextOpe == null && previousOpe == null && globalNextOpe == null)
                return splitFilter(subExpressionContent);
        }

        // ((?:(?!\sAND\s|\sOR\s).)+)(\sAND\s|\sOR\s)* with last occurence !
        Pattern patternOperator = Pattern.compile("((?:(?!\\sAND\\s|\\sOR\\s).)+)(\\sAND\\s|\\sOR\\s)", Pattern.CASE_INSENSITIVE);

        if (patternOperator.matcher(filter).find()) {
            Matcher matcherOperator = patternOperator.matcher(filter);
            while (matcherOperator.find()) {
                String expression = matcherOperator.group(1).trim();
                String operator = matcherOperator.group(2).trim();
                if (globalNextOpe != null) {
                    if (globalNextOpe.equals("AND")) {
                        listAndOperator.add(regexFilterField(expression));
                    } else if (globalNextOpe.equals("OR")) {
                        listOrOperator.add(regexFilterField(expression));
                    }
                }
                //////////////////////
                if (operator.equals("AND")) {
                    globalNextOpe = "AND";
                    listAndOperator.add(regexFilterField(expression));
                } else if (operator.equals("OR")) {
                    globalNextOpe = "OR";
                    listOrOperator.add(regexFilterField(expression));
                }
            }

            // last block of expression
            Matcher matcherLastOccurence = Pattern.compile("(?:.(?!(\\sAND\\s|\\sOR\\s)))+?$", Pattern.CASE_INSENSITIVE).matcher(filter);
            if (matcherLastOccurence.find()) {
                String operator = matcherLastOccurence.group(1).trim();
                String expression = matcherLastOccurence.group(0).trim().replace(operator, "");

                if (operator.equals("AND")) {
                    listAndOperator.add(regexFilterField(expression));
                } else if (operator.equals("OR")) {
                    listOrOperator.add(regexFilterField(expression));
                }
            }
        }
        // simple case key = value !
        else if (Pattern.compile("(([^><!=]|(?!MATCH))+)(([><!=]|(\\sMATCH\\s))+)(([^><!=]|(?!MATCH))+)", Pattern.CASE_INSENSITIVE).matcher(filter).find()) {  // simple case
            criteria = regexFilterField(filter);
            if (globalNextOpe != null) {
                if (globalNextOpe.equals("AND"))
                    listAndOperator.add(criteria);
                else
                    listOrOperator.add(criteria);
            }
        }

        if (listAndOperator.size() != 0 || listOrOperator.size() != 0) {
            allParms.put("andList", listAndOperator);
            allParms.put("orList", listOrOperator);
            criteria = criteriaOperatorBuilder(allParms);
        }

        return criteria;
    }

    private static Criteria regexFilterField(String subString) throws Exception { // champs="valeur"
        Pattern r = Pattern.compile("(([^><!=]|(?!MATCH))+)(([><!=]|(\\sMATCH\\s))+)(([^><!=]|(?!MATCH))+)", Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(subString);
        if (m.find()) {
            String field = replaceUnexpectedChar(m.group(1)); // field
            String ope = m.group(3).trim(); // operator =<>!=
            String value = m.group(6); // value
            return queryFilter(field, ope, value);
        }
        throw new Exception("Pattern matcher doesn't deserve some result ([^<>!=]+)([><!=]*)([^><!=]+)");
    }


    public static void checkFilterValidity(String filter) throws Exception {
        if (Pattern.compile("(\\sAND\\s|\\sOR\\s)", Pattern.CASE_INSENSITIVE).matcher(filter).find()) {
            Arrays.asList(filter.split("(\\sAND\\s|\\sOR\\s)", Pattern.CASE_INSENSITIVE)).forEach(e ->
            {
                try {
                    checksubFilterValidity(e);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            checksubFilterValidity(filter);
        }
    }

    private static void checksubFilterValidity(String filter) throws Exception {
        String pattern = "([<>=].+|MATCH.+)+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(filter);
        if (!m.find()) {
            throw new Exception("pattern non exploitable en tant que filtre");
        }
    }

    private static String replaceUnexpectedChar(String subString) {
        subString = subString.replace("\"", "")
                .replace("*", "%")
                .replaceFirst("^\\s+", "")
                .replaceFirst("\\s+$", "")
                .replace("(", "")
                .replace(")", "");
        return subString;
    }

    public static Criteria queryFilter(String field, String operator, String value) throws Exception {
        Object v =  replaceUnexpectedChar(value);

        if (!value.contains("\"")) { // gestion des cas du genre id="51" => ne doit pas etre converti en nombre
            value = replaceUnexpectedChar(value);
            if (Pattern.compile("^\\d+-\\d+-\\d+T?.+$", Pattern.CASE_INSENSITIVE).matcher(value.toString()).find()) {
                v = DateUtils.stringToInstant(value.toString());
            } else if (Pattern.compile("^-?\\d*\\.+\\d+$", Pattern.CASE_INSENSITIVE).matcher(value.toString()).find()) {
                v = Float.parseFloat(value.toString());
            } else if (Pattern.compile("^-?\\d*$", Pattern.CASE_INSENSITIVE).matcher(value.toString()).find()) {
                v = Integer.parseInt(value.toString());
            }
        }

        switch (CriteriaFilter.valueOf(convertFilterToCriteriaFilter(operator))) {
            case IS:
                return Criteria.where(field).is(v);
            case LIKE:
                return Criteria.where(field).regex(".*" + v + ".*", "i");
            case MATCH:
                return Criteria.where(field).regex(".*" + v + ".*", "i");
            case LIKELAST:
                return Criteria.where(field).regex(".*" + v, "i");
            case LIKEFIRST:
                return Criteria.where(field).regex(v + ".*", "i");
            case NOT:
                return Criteria.where(field).ne(v);
            case LT:
                return Criteria.where(field).lt(v);
            case LTE:
                return Criteria.where(field).lte(v);
            case GT:
                return Criteria.where(field).gt(v);
            case GTE:
                return Criteria.where(field).gte(v);
        }
        return null;
        // throw error invalid argument !
    }

    public static String convertFilterToCriteriaFilter(String filter) throws Exception {
        switch (filter.toUpperCase()) {
            case "<":
                return "LT";
            case "<=":
                return "LTE";
            case ">":
                return "GT";
            case ">=":
                return "GTE";
            case "=":
                return "IS";
            case "!=":
                return "NOT";
            case "MATCH":
                return "LIKE";
            default:
                throw new Exception("Filtre non existant dans l'implementation des criteria {" + filter + "}");
        }
    }

    public static Criteria queryEquals(String field, Object value) {
        return Criteria.where(field).is(value);
    }

}
