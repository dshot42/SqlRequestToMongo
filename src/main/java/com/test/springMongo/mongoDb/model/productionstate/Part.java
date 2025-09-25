package com.test.springMongo.mongoDb.model.productionstate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.springMongo.mongoDb.model.ElementEntity;
import com.test.springMongo.mongoDb.model.sensors.SensorValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//@Indices({ @Index(value = "partId", type = IndexType.NonUnique), @Index(value = "state", type = IndexType.NonUnique),
//		@Index(value = "associatedWorkorder", type = IndexType.NonUnique),
//		@Index(value = "lastModified", type = IndexType.NonUnique) })
@Document("Part")
public class Part extends ElementEntity implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "part_sequence";

    private static Logger logger = LoggerFactory.getLogger(Part.class);

    public static final String PARTID_FIELD = "partId";


    private static final long serialVersionUID = -1286571795283029644L;

    /**
     * default constructor
     */
    public Part() {
    }

    @Id
    // @JsonSchema(title = "Part Internal ID", metadata = @JSData(key = "order", value = "10"))
    @JsonProperty
    public String id;

    public String getId() {
        return id;
    }

    // @JsonSchema(title = "Part Current state", metadata = @JSData(key = "order", value = "70"))
    @JsonProperty
    public String state;

    public String getState() {
        return state;
    }

    // @JsonSchema(title = "Associated Workorder", metadata = @JSData(key = "order", value = "50"))
    @JsonProperty
    protected Workorder associatedWorkorder;

    /**
     * part ID, customer id, to know the current part
     */
    // @JsonSchema(title = "Part User ID", metadata = @JSData(key = "order", value = "30"))
    @JsonProperty
    public String partId;

    /**
     * the current process step, setted when a partIn is sent
     */
    // @JsonSchema(title = "Current Part Step", metadata = @JSData(key = "order", value = "90"))
    @JsonProperty
    protected String currentStepForPart = null;

    /**
     * if the part is in a given step, defining the time stamp
     */
    // @JsonSchema(title = "Current Part Step entering time", metadata = @JSData(key = "order", value = "95"))
    @JsonProperty
    protected String currentStepForPartEnteringTime = null;

    /**
     * the latest known passed step for part
     */
    // @JsonSchema(title = "Latest Part Step", metadata = @JSData(key = "order", value = "90"))
    @JsonProperty
    protected String latestStepForPart = null;

    /**
     * the current part history
     */
    // @JsonSchema(title = "Last Part History")
    @JsonProperty
    protected PartHistory lastPartHistory = null;

    /**
     * defective or completeness of the part
     */
    // @JsonSchema(title = "Defective", metadata = @JSData(key = "order", value = "110"))
    @JsonProperty
    protected boolean defective = false;

    // @JsonSchema(title = "Completed", metadata = @JSData(key = "order", value = "130"))
    // does part has end processing - defective or good
    @JsonProperty
    protected boolean completed = false;

    /**
     * by step the part history informations, key is the step
     */
    // @JsonSchema(title = "Latest Step Part History", metadata = @JSData(key = "order", value = "150"))
    @JsonProperty
    protected HashMap<String, PartHistory> history = new HashMap<>();

    /**
     * all steps passed by the part (even if the part passed multiple time in
     * production step)
     */
    // @JsonSchema(title = "All Part Step History", metadata = @JSData(key = "order", value = "170"))
    @JsonProperty
    protected LinkedList<PartHistory> allStepHistory = new LinkedList<>();

    /**
     * associated sensor value, that can be associated to the part while processing
     */
    // @JsonSchema(title = "Associated Sensor Values, with steps", metadata = @JSData(key = "order", value = "190"))
    @JsonProperty
    protected ArrayList<SensorValue> associatedSensorValues = new ArrayList<>();

    // pending associated machine defects
    // @JsonSchema(title = "Associated machine defects arrived while processing", metadata = @JSData(key = "order", value = "195"))
//	protected List<AbstractMachineDefect> pendingAssociatedMachineDefects = new ArrayList<>();

    // additional informations populated by the implementer
    // @JsonSchema(title = "User additional informations", metadata = @JSData(key = "order", value = "197"))
    @JsonProperty
    protected HashMap<String, Object> userAdditionalInformations = new HashMap<>();

    /**
     * information for the last storage of the part information
     */
    public String lastModified;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        Part.logger = logger;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Workorder getAssociatedWorkorder() {
        return associatedWorkorder;
    }

    public void setAssociatedWorkorder(Workorder associatedWorkorder) {
        this.associatedWorkorder = associatedWorkorder;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getCurrentStepForPart() {
        return currentStepForPart;
    }

    public void setCurrentStepForPart(String currentStepForPart) {
        this.currentStepForPart = currentStepForPart;
    }

    public String getCurrentStepForPartEnteringTime() {
        return currentStepForPartEnteringTime;
    }

    public void setCurrentStepForPartEnteringTime(String currentStepForPartEnteringTime) {
        this.currentStepForPartEnteringTime = currentStepForPartEnteringTime;
    }

    public String getLatestStepForPart() {
        return latestStepForPart;
    }

    public void setLatestStepForPart(String latestStepForPart) {
        this.latestStepForPart = latestStepForPart;
    }

    public PartHistory getLastPartHistory() {
        return lastPartHistory;
    }

    public void setLastPartHistory(PartHistory lastPartHistory) {
        this.lastPartHistory = lastPartHistory;
    }

    public boolean isDefective() {
        return defective;
    }

    public void setDefective(boolean defective) {
        this.defective = defective;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public HashMap<String, PartHistory> getHistory() {
        return history;
    }

    public void setHistory(HashMap<String, PartHistory> history) {
        this.history = history;
    }

    public LinkedList<PartHistory> getAllStepHistory() {
        return allStepHistory;
    }

    public void setAllStepHistory(LinkedList<PartHistory> allStepHistory) {
        this.allStepHistory = allStepHistory;
    }

    public ArrayList<SensorValue> getAssociatedSensorValues() {
        return associatedSensorValues;
    }

    public void setAssociatedSensorValues(ArrayList<SensorValue> associatedSensorValues) {
        this.associatedSensorValues = associatedSensorValues;
    }

//	public List<AbstractMachineDefect> getPendingAssociatedMachineDefects() {
//		return pendingAssociatedMachineDefects;
//	}
//
//	public void setPendingAssociatedMachineDefects(List<AbstractMachineDefect> pendingAssociatedMachineDefects) {
//		this.pendingAssociatedMachineDefects = pendingAssociatedMachineDefects;
//	}

    public HashMap<String, Object> getUserAdditionalInformations() {
        return userAdditionalInformations;
    }

    public void setUserAdditionalInformations(HashMap<String, Object> userAdditionalInformations) {
        this.userAdditionalInformations = userAdditionalInformations;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }


    //	public String getPartId() {
//		return partId;
//	}
//
//	public void setPartId(String partId) {
//		this.partId = partId;
//	}
//
//	public String getCurrentStepForPart() {
//		return currentStepForPart;
//	}
//
//	public void setCurrentStepForPart(String currentStepForPart) {
//		this.currentStepForPart = currentStepForPart;
//	}
//
//	public void setCurrentStepForPartEnteringTime(Instant currentStepForPartEnteringTime) {
//		this.currentStepForPartEnteringTime = currentStepForPartEnteringTime;
//	}
//
//	public String getCurrentStepForPartEnteringTime() {
//		return currentStepForPartEnteringTime;
//	}
//
//	public String getLatestStepForPart() {
//		return latestStepForPart;
//	}
//
//	public void setLatestStepForPart(String latestStepForPart) {
//		this.latestStepForPart = latestStepForPart;
//	}
//
//	public PartHistory getLastPartHistory() {
//		return lastPartHistory;
//	}
//
//	public void setLastPartHistory(PartHistory lastPartHistory) {
//		this.lastPartHistory = lastPartHistory;
//	}
//
//	public Map<String, PartHistory> getHistory() {
//		return history;
//	}
//
//	public void setHistory(HashMap<String, PartHistory> history) {
//		assert history != null;
//		for (String k : history.keySet()) {
//			if (k == null || k.isBlank()) {
//				throw new RuntimeException("history passed :" + history + " has null keys");
//			}
//		}
//		this.history = history;
//	}
//
//	public ArrayList<SensorValue> getAssociatedSensorValues() {
//		return associatedSensorValues;
//	}
//
//	public void setAssociatedSensorValues(ArrayList<SensorValue> associatedSensorValues) {
//		this.associatedSensorValues = associatedSensorValues;
//	}
//
//	/**
//	 * populate this object with PartStepOutEvents
//	 *
//	 * @param event
//	 */
//	public void receivePartOutput(ImmutablePartStepOutEvt event, Map<String, Object> stepParameters) {
//
//		logger.debug("receiving part output with id " + event.getPartId());
//
//		PartHistory partHistory = null;
//
//		String eventStepName = event.getStep();
//		assert eventStepName != null;
//
//		if (eventStepName == null) {
//			throw new RuntimeException("step in event is null : " + SerializationUtils.serializeForDebug(event));
//		}
//
//		// get part workorder parameters
//		Instant entering = null;
//		if (currentStepForPartEnteringTime != null) {
//			entering = currentStepForPartEnteringTime;
//		}
//
//		if (lastPartHistory == null) {
//			// if no informations about the part step entry,
//			// create one with no time stamp at begin
//			logger.debug("no last history available");
//
//			partHistory = new PartHistory(eventStepName, stepParameters, event.getMeasures(), entering,
//					/* exiting */ event.getTimestamp(), event.getResultCode());
//
//		} else {
//
//			// if we are in the same step, close the event
//			assert lastPartHistory != null;
//
//			if (entering == null && lastPartHistory != null) {
//				entering = lastPartHistory.stepExitingTimeStamp;
//			}
//
//			logger.debug("use the last output history information, for starting");
//
//			// we have the information about the latest step
//			partHistory = new PartHistory(eventStepName, stepParameters, event.getMeasures(), entering,
//					(Instant) event.getTimestamp(), event.getResultCode());
//
//		}
//
//		/////////////////////////////////////////////////////
//		// enqueue to history
//
//		enqueueToHistory(partHistory, eventStepName);
//
//		// define the latest step
//		lastPartHistory = partHistory;
//
//		currentStepForPart = null;
//		currentStepForPartEnteringTime = null;
//
//	}
//
//	/**
//	 * this method enqueue the part history for the given stepName
//	 *
//	 * @param partHistory
//	 * @param stepName
//	 */
//	protected void enqueueToHistory(PartHistory partHistory, String stepName) {
//
//		// checks
//
//		if (stepName == null || stepName.isBlank()) {
//			throw new RuntimeException("null stepname cannot be added to part history");
//		}
//
//		assert stepName != null;
//		if (!stepName.equals(partHistory.AtStep)) {
//			throw new RuntimeException("stepName is not equals to PartHistory elements");
//		}
//
//		////////////////////////////////////////////////
//
//		if (pendingAssociatedMachineDefects != null && pendingAssociatedMachineDefects.size() > 0) {
//
//			// by ref
//			List<MachineDefect> encounteredMachineDefects = partHistory.getEncounteredMachineDefects();
//
//			for (AbstractMachineDefect abstractMachineDefect : pendingAssociatedMachineDefects) {
//				if (abstractMachineDefect instanceof ImmutableMachineDefect) {
//					encounteredMachineDefects.add(new MachineDefect((ImmutableMachineDefect) abstractMachineDefect));
//				} else if (abstractMachineDefect instanceof ImmutableMachineDefectUnknown) {
//					encounteredMachineDefects
//							.add(new MachineDefect((ImmutableMachineDefectUnknown) abstractMachineDefect));
//				} else {
//					logger.debug("machine defect {} not added to ", abstractMachineDefect);
//				}
//			}
//
//		}
//
//		history.put(stepName, partHistory);
//		allStepHistory.add(partHistory);
//
//		// clear pending
//		pendingAssociatedMachineDefects.clear();
//
//		logger.debug("Merging data into Part agent " + partId + " new history is: ");
//		if (logger.isDebugEnabled()) {
//
//			logger.debug("state history:");
//			history.entrySet().forEach(entry -> {
//				logger.debug("   " + entry.getKey() + " " + entry.getValue());
//			});
//
//			logger.debug("all parts history:");
//			allStepHistory.forEach((ph) -> {
//				logger.debug("   " + ph);
//			});
//
//		}
//
//	}
//
//	/**
//	 * handling machine defect for part state changes
//	 *
//	 * the default implementation add it to the list of
//	 * pendingAssociatedMachineDefects array
//	 *
//	 * can be overloaded in derived class to adjust members
//	 *
//	 * @param machineDefect
//	 */
//	public void machineDefectArrived(AbstractMachineDefect machineDefect) {
//		assert machineDefect != null;
//		if (machineDefect != null) {
//			logger.debug("adding machine defect");
//			this.pendingAssociatedMachineDefects.add(machineDefect);
//		} else {
//			try {
//				throw new Exception("Exception for stackTrace");
//			} catch (Exception fakeExceptionToLogTheLocation) {
//				logger.debug(
//						"IMPLEMENTATION ERROR, in implementation, we expect the machine defect to not be null, it is null !!",
//						fakeExceptionToLogTheLocation);
//			}
//		}
//	}
//
//	public static class TimeBounds {
//		public String start = null;
//		public String end = null;
//	}
//
//	public static Instant max(Instant t1, Instant t2) {
//		if (t1 == null) {
//			return t2;
//		}
//		if (t2 == null) {
//			return t1;
//		}
//
//		if (t2.toEpochMilli() > t1.toEpochMilli()) {
//			return t2;
//		}
//		return t1;
//	}
//
//	public static Instant min(Instant t1, Instant t2) {
//		if (t1 == null) {
//			return t2;
//		}
//		if (t2 == null) {
//			return t1;
//		}
//
//		if (t2.toEpochMilli() < t1.toEpochMilli()) {
//			return t2;
//		}
//		return t1;
//	}
//
//	/**
//	 * compute the time bounds of part building
//	 *
//	 * @return
//	 */
//	public TimeBounds computeStartFromSteps() {
//
//		TimeBounds b = new TimeBounds();
//
//		history.entrySet().forEach((h) -> {
//
//			PartHistory v = h.getValue();
//			assert v != null;
//			b.start = min(b.start, v.stepEnteringTimeStamp);
//			b.start = min(b.start, v.stepExitingTimeStamp);
//
//			b.end = max(b.end, v.stepEnteringTimeStamp);
//			b.end = max(b.end, v.stepExitingTimeStamp);
//
//		});
//
//		return b;
//	}
//
//	/**
//	 * call by the part actor when some part data evt is received
//	 *
//	 * can be derived if overload is needed
//	 *
//	 * @param event
//	 * @param stepParameters
//	 */
//	public void receivePartData(ImmutablePartDataEvt event, Map<String, Object> stepParameters) {
//		PartHistory dataHistory = new PartHistory(event.getStep(), stepParameters, event.getMeasures(), null,
//				event.getTimestamp(), 0);
//
//		// insert data to existing data
//		enqueueToHistory(dataHistory, event.getStep());
//
//	}
//
//	public boolean isCompleted() {
//		return completed;
//	}
//
//	public void setCompleted(boolean completed) {
//		this.completed = completed;
//	}
//
//	public boolean isDefective() {
//		return defective;
//	}
//
//	public void setDefective() {
//		this.defective = true;
//	}
//
//	public void recoverFromDefectiveState() {
//		this.defective = false;
//	}
//
//	public void changeWorkorder(String workorder) {
//		if (this.associatedWorkorder != null && !this.associatedWorkorder.equals(workorder)) {
//			logger.warn(String.format("part %s  has currenlty workorder %s, this will be changed by %s", partId,
//					associatedWorkorder, workorder));
//		}
//		this.associatedWorkorder = workorder;
//	}
//
//	/**
//	 * this function permit to user code to take incoming values and adjust the part
//	 * informations
//	 *
//	 * @param incomingInformation
//	 */
//	public void adjustValuesFromInfomingInformation(Object incomingInformation) throws Exception {
//
//	}
//
//	/**
//	 * get a reference of all step history
//	 *
//	 * @return
//	 */
//	public LinkedList<PartHistory> getAllStepHistory() {
//		return allStepHistory;
//	}
//
//	public LinkedList<PartHistory> setAllStepHistory(LinkedList<PartHistory> listPartHistory) {
//		this.allStepHistory = listPartHistory;
//		return allStepHistory;
//	}
//
//	/**
//	 * get associated workorder
//	 *
//	 * @return
//	 */
//	public String getAssociatedWorkorder() {
//		return associatedWorkorder;
//	}
//
//	/**
//	 * get user additional Information, used in history archiving, permit to derived
//	 * class to add specifically implemented information to the historian
//	 *
//	 * @return
//	 */
//	public HashMap<String, Object> getUserAdditionalInformations() {
//		return userAdditionalInformations;
//	}
//
//
//	public List<AbstractMachineDefect> getPendingAssociatedMachineDefects() {
//		return pendingAssociatedMachineDefects;
//	}
//
//	public void setPendingAssociatedMachineDefects(List<AbstractMachineDefect> pendingAssociatedMachineDefects) {
//		if (pendingAssociatedMachineDefects == null) {
//			pendingAssociatedMachineDefects = new ArrayList<>();
//		}
//		this.pendingAssociatedMachineDefects = pendingAssociatedMachineDefects;
//	}

}
