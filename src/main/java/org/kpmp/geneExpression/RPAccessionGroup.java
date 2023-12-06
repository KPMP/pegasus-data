package org.kpmp.geneExpression;
public class RPAccessionGroup {
        private String accession;
        private RPExpressionByTissueType rpExpressionByTissueType;

        public RPAccessionGroup() {
                this.accession = null;
                this.rpExpressionByTissueType = null;
        }
        public RPAccessionGroup(String accession, RPExpressionByTissueType rpExpressionByTissueType) {
                this.accession = accession;
                this.rpExpressionByTissueType = rpExpressionByTissueType;
        }

        public String getAccession() {
                return accession;
        }

        public void setAccession(String accession) {
                this.accession = accession;
        }

        public RPExpressionByTissueType getRpExpressionByTissueType() {
                return rpExpressionByTissueType;
        }

        public void setRpExpressionByTissueType(RPExpressionByTissueType rpExpressionByTissueType) {
                this.rpExpressionByTissueType = rpExpressionByTissueType;
        }
}
