package org.kpmp;

public enum EnrollmentCategoryEnum {

	ALL("all", "all"), AKI("aki", "AKI"), CKD("ckd", "CKD"), HEALTHY_REFERENCE("hrt", "Healthy Reference"),
	DMR("dmr", "DM-R"), UNKNOWN("", "");

	private String requestType;
	private String participantEnrollmentCategory;

	private EnrollmentCategoryEnum(String requestType, String participantEnrollmentCategory) {
		this.requestType = requestType;
		this.participantEnrollmentCategory = participantEnrollmentCategory;
	}

	public String getParticipantEnrollmentCategory() {
		return this.participantEnrollmentCategory;
	}

    public String getRequestType() {
        return this.requestType;
    }


    public static EnrollmentCategoryEnum fromRequestType(String requestType) {
		if (ALL.requestType.equalsIgnoreCase(requestType)) {
			return ALL;
		} else if (AKI.requestType.equalsIgnoreCase(requestType)) {
			return AKI;
		} else if (CKD.requestType.equalsIgnoreCase(requestType)) {
			return CKD;
		} else if (HEALTHY_REFERENCE.requestType.equalsIgnoreCase(requestType)) {
			return HEALTHY_REFERENCE;
		}  else if (DMR.requestType.equalsIgnoreCase(requestType)) {
			return DMR;
		}
		else {
			return UNKNOWN;
		}
	}

}
