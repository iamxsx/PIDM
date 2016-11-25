package com.oneclouder.model;

import java.util.ArrayList;
import java.util.List;

public class TCompanyEmployeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCompanyEmployeeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andJobPositionIsNull() {
            addCriterion("job_position is null");
            return (Criteria) this;
        }

        public Criteria andJobPositionIsNotNull() {
            addCriterion("job_position is not null");
            return (Criteria) this;
        }

        public Criteria andJobPositionEqualTo(String value) {
            addCriterion("job_position =", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionNotEqualTo(String value) {
            addCriterion("job_position <>", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionGreaterThan(String value) {
            addCriterion("job_position >", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionGreaterThanOrEqualTo(String value) {
            addCriterion("job_position >=", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionLessThan(String value) {
            addCriterion("job_position <", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionLessThanOrEqualTo(String value) {
            addCriterion("job_position <=", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionLike(String value) {
            addCriterion("job_position like", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionNotLike(String value) {
            addCriterion("job_position not like", value, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionIn(List<String> values) {
            addCriterion("job_position in", values, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionNotIn(List<String> values) {
            addCriterion("job_position not in", values, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionBetween(String value1, String value2) {
            addCriterion("job_position between", value1, value2, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andJobPositionNotBetween(String value1, String value2) {
            addCriterion("job_position not between", value1, value2, "jobPosition");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumIsNull() {
            addCriterion("office_phone_num is null");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumIsNotNull() {
            addCriterion("office_phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumEqualTo(String value) {
            addCriterion("office_phone_num =", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumNotEqualTo(String value) {
            addCriterion("office_phone_num <>", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumGreaterThan(String value) {
            addCriterion("office_phone_num >", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("office_phone_num >=", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumLessThan(String value) {
            addCriterion("office_phone_num <", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumLessThanOrEqualTo(String value) {
            addCriterion("office_phone_num <=", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumLike(String value) {
            addCriterion("office_phone_num like", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumNotLike(String value) {
            addCriterion("office_phone_num not like", value, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumIn(List<String> values) {
            addCriterion("office_phone_num in", values, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumNotIn(List<String> values) {
            addCriterion("office_phone_num not in", values, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumBetween(String value1, String value2) {
            addCriterion("office_phone_num between", value1, value2, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNumNotBetween(String value1, String value2) {
            addCriterion("office_phone_num not between", value1, value2, "officePhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumIsNull() {
            addCriterion("cell_phone_num is null");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumIsNotNull() {
            addCriterion("cell_phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumEqualTo(String value) {
            addCriterion("cell_phone_num =", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumNotEqualTo(String value) {
            addCriterion("cell_phone_num <>", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumGreaterThan(String value) {
            addCriterion("cell_phone_num >", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("cell_phone_num >=", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumLessThan(String value) {
            addCriterion("cell_phone_num <", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("cell_phone_num <=", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumLike(String value) {
            addCriterion("cell_phone_num like", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumNotLike(String value) {
            addCriterion("cell_phone_num not like", value, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumIn(List<String> values) {
            addCriterion("cell_phone_num in", values, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumNotIn(List<String> values) {
            addCriterion("cell_phone_num not in", values, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumBetween(String value1, String value2) {
            addCriterion("cell_phone_num between", value1, value2, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andCellPhoneNumNotBetween(String value1, String value2) {
            addCriterion("cell_phone_num not between", value1, value2, "cellPhoneNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumIsNull() {
            addCriterion("fax_num is null");
            return (Criteria) this;
        }

        public Criteria andFaxNumIsNotNull() {
            addCriterion("fax_num is not null");
            return (Criteria) this;
        }

        public Criteria andFaxNumEqualTo(String value) {
            addCriterion("fax_num =", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumNotEqualTo(String value) {
            addCriterion("fax_num <>", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumGreaterThan(String value) {
            addCriterion("fax_num >", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumGreaterThanOrEqualTo(String value) {
            addCriterion("fax_num >=", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumLessThan(String value) {
            addCriterion("fax_num <", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumLessThanOrEqualTo(String value) {
            addCriterion("fax_num <=", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumLike(String value) {
            addCriterion("fax_num like", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumNotLike(String value) {
            addCriterion("fax_num not like", value, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumIn(List<String> values) {
            addCriterion("fax_num in", values, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumNotIn(List<String> values) {
            addCriterion("fax_num not in", values, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumBetween(String value1, String value2) {
            addCriterion("fax_num between", value1, value2, "faxNum");
            return (Criteria) this;
        }

        public Criteria andFaxNumNotBetween(String value1, String value2) {
            addCriterion("fax_num not between", value1, value2, "faxNum");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionIsNull() {
            addCriterion("asct_job_position is null");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionIsNotNull() {
            addCriterion("asct_job_position is not null");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionEqualTo(String value) {
            addCriterion("asct_job_position =", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionNotEqualTo(String value) {
            addCriterion("asct_job_position <>", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionGreaterThan(String value) {
            addCriterion("asct_job_position >", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionGreaterThanOrEqualTo(String value) {
            addCriterion("asct_job_position >=", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionLessThan(String value) {
            addCriterion("asct_job_position <", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionLessThanOrEqualTo(String value) {
            addCriterion("asct_job_position <=", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionLike(String value) {
            addCriterion("asct_job_position like", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionNotLike(String value) {
            addCriterion("asct_job_position not like", value, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionIn(List<String> values) {
            addCriterion("asct_job_position in", values, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionNotIn(List<String> values) {
            addCriterion("asct_job_position not in", values, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionBetween(String value1, String value2) {
            addCriterion("asct_job_position between", value1, value2, "asctJobPosition");
            return (Criteria) this;
        }

        public Criteria andAsctJobPositionNotBetween(String value1, String value2) {
            addCriterion("asct_job_position not between", value1, value2, "asctJobPosition");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}