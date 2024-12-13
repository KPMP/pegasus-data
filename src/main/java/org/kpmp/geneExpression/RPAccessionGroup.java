package org.kpmp.geneExpression;
public class RPAccessionGroup {
        private String accession;
        private RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory;

        public RPAccessionGroup() {
                this.accession = null;
                this.rpExpressionByEnrollmentCategory = null;
        }
        public RPAccessionGroup(String accession, RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory) {
                this.accession = accession;
                this.rpExpressionByEnrollmentCategory = rpExpressionByEnrollmentCategory;
        }

        public String getAccession() {
                return accession;
        }

        public void setAccession(String accession) {
                this.accession = accession;
        }

        public RPExpressionByEnrollmentCategory getRpExpressionByEnrollmentCategory() {
                return rpExpressionByEnrollmentCategory;
        }

        public void setRpExpressionByEnrollmentCategory(RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory) {
                this.rpExpressionByEnrollmentCategory = rpExpressionByEnrollmentCategory;
        }
}
