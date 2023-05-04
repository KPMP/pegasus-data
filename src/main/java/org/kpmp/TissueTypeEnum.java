package org.kpmp;

public enum TissueTypeEnum {

	ALL("all", "all"), AKI("aki", "AKI"), CKD("ckd", "CKD"), HEALTHY_REFERENCE("hrt", "Healthy Reference"),
	RESISTOR("resistor", "resistor"), UNKNOWN("", "");

	private String requestType;
	private String participantTissueType;

	private TissueTypeEnum(String requestType, String participantTissueType) {
		this.requestType = requestType;
		this.participantTissueType = participantTissueType;
	}

	public String getParticipantTissueType() {
		return this.participantTissueType;
	}

    public String getRequestType() {
        return this.requestType;
    }


    public static TissueTypeEnum fromRequestType(String requestType) {
		if (ALL.requestType.equalsIgnoreCase(requestType)) {
			return ALL;
		} else if (AKI.requestType.equalsIgnoreCase(requestType)) {
			return AKI;
		} else if (CKD.requestType.equalsIgnoreCase(requestType)) {
			return CKD;
		} else if (HEALTHY_REFERENCE.requestType.equalsIgnoreCase(requestType)) {
			return HEALTHY_REFERENCE;
		}  else if (RESISTOR.requestType.equalsIgnoreCase(requestType)) {
			return RESISTOR;
		}
		else {
			return UNKNOWN;
		}
	}

}
