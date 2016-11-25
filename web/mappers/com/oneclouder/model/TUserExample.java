package com.oneclouder.model;

import java.util.ArrayList;
import java.util.List;

public class TUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUserExample() {
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andIdcartIsNull() {
            addCriterion("IDCart is null");
            return (Criteria) this;
        }

        public Criteria andIdcartIsNotNull() {
            addCriterion("IDCart is not null");
            return (Criteria) this;
        }

        public Criteria andIdcartEqualTo(String value) {
            addCriterion("IDCart =", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartNotEqualTo(String value) {
            addCriterion("IDCart <>", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartGreaterThan(String value) {
            addCriterion("IDCart >", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartGreaterThanOrEqualTo(String value) {
            addCriterion("IDCart >=", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartLessThan(String value) {
            addCriterion("IDCart <", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartLessThanOrEqualTo(String value) {
            addCriterion("IDCart <=", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartLike(String value) {
            addCriterion("IDCart like", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartNotLike(String value) {
            addCriterion("IDCart not like", value, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartIn(List<String> values) {
            addCriterion("IDCart in", values, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartNotIn(List<String> values) {
            addCriterion("IDCart not in", values, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartBetween(String value1, String value2) {
            addCriterion("IDCart between", value1, value2, "idcart");
            return (Criteria) this;
        }

        public Criteria andIdcartNotBetween(String value1, String value2) {
            addCriterion("IDCart not between", value1, value2, "idcart");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAssociationIdIsNull() {
            addCriterion("association_id is null");
            return (Criteria) this;
        }

        public Criteria andAssociationIdIsNotNull() {
            addCriterion("association_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssociationIdEqualTo(String value) {
            addCriterion("association_id =", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdNotEqualTo(String value) {
            addCriterion("association_id <>", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdGreaterThan(String value) {
            addCriterion("association_id >", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdGreaterThanOrEqualTo(String value) {
            addCriterion("association_id >=", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdLessThan(String value) {
            addCriterion("association_id <", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdLessThanOrEqualTo(String value) {
            addCriterion("association_id <=", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdLike(String value) {
            addCriterion("association_id like", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdNotLike(String value) {
            addCriterion("association_id not like", value, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdIn(List<String> values) {
            addCriterion("association_id in", values, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdNotIn(List<String> values) {
            addCriterion("association_id not in", values, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdBetween(String value1, String value2) {
            addCriterion("association_id between", value1, value2, "associationId");
            return (Criteria) this;
        }

        public Criteria andAssociationIdNotBetween(String value1, String value2) {
            addCriterion("association_id not between", value1, value2, "associationId");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactIsNull() {
            addCriterion("designated_contact is null");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactIsNotNull() {
            addCriterion("designated_contact is not null");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactEqualTo(Integer value) {
            addCriterion("designated_contact =", value, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactNotEqualTo(Integer value) {
            addCriterion("designated_contact <>", value, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactGreaterThan(Integer value) {
            addCriterion("designated_contact >", value, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactGreaterThanOrEqualTo(Integer value) {
            addCriterion("designated_contact >=", value, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactLessThan(Integer value) {
            addCriterion("designated_contact <", value, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactLessThanOrEqualTo(Integer value) {
            addCriterion("designated_contact <=", value, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactIn(List<Integer> values) {
            addCriterion("designated_contact in", values, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactNotIn(List<Integer> values) {
            addCriterion("designated_contact not in", values, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactBetween(Integer value1, Integer value2) {
            addCriterion("designated_contact between", value1, value2, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andDesignatedContactNotBetween(Integer value1, Integer value2) {
            addCriterion("designated_contact not between", value1, value2, "designatedContact");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIsNull() {
            addCriterion("register_status is null");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIsNotNull() {
            addCriterion("register_status is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusEqualTo(Integer value) {
            addCriterion("register_status =", value, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNotEqualTo(Integer value) {
            addCriterion("register_status <>", value, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusGreaterThan(Integer value) {
            addCriterion("register_status >", value, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("register_status >=", value, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusLessThan(Integer value) {
            addCriterion("register_status <", value, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusLessThanOrEqualTo(Integer value) {
            addCriterion("register_status <=", value, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIn(List<Integer> values) {
            addCriterion("register_status in", values, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNotIn(List<Integer> values) {
            addCriterion("register_status not in", values, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusBetween(Integer value1, Integer value2) {
            addCriterion("register_status between", value1, value2, "registerStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("register_status not between", value1, value2, "registerStatus");
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

        public Criteria andFrequentContactIsNull() {
            addCriterion("frequent_contact is null");
            return (Criteria) this;
        }

        public Criteria andFrequentContactIsNotNull() {
            addCriterion("frequent_contact is not null");
            return (Criteria) this;
        }

        public Criteria andFrequentContactEqualTo(Integer value) {
            addCriterion("frequent_contact =", value, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactNotEqualTo(Integer value) {
            addCriterion("frequent_contact <>", value, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactGreaterThan(Integer value) {
            addCriterion("frequent_contact >", value, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactGreaterThanOrEqualTo(Integer value) {
            addCriterion("frequent_contact >=", value, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactLessThan(Integer value) {
            addCriterion("frequent_contact <", value, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactLessThanOrEqualTo(Integer value) {
            addCriterion("frequent_contact <=", value, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactIn(List<Integer> values) {
            addCriterion("frequent_contact in", values, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactNotIn(List<Integer> values) {
            addCriterion("frequent_contact not in", values, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactBetween(Integer value1, Integer value2) {
            addCriterion("frequent_contact between", value1, value2, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andFrequentContactNotBetween(Integer value1, Integer value2) {
            addCriterion("frequent_contact not between", value1, value2, "frequentContact");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdIsNull() {
            addCriterion("asct_unit_id is null");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdIsNotNull() {
            addCriterion("asct_unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdEqualTo(Integer value) {
            addCriterion("asct_unit_id =", value, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdNotEqualTo(Integer value) {
            addCriterion("asct_unit_id <>", value, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdGreaterThan(Integer value) {
            addCriterion("asct_unit_id >", value, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("asct_unit_id >=", value, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdLessThan(Integer value) {
            addCriterion("asct_unit_id <", value, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("asct_unit_id <=", value, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdIn(List<Integer> values) {
            addCriterion("asct_unit_id in", values, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdNotIn(List<Integer> values) {
            addCriterion("asct_unit_id not in", values, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("asct_unit_id between", value1, value2, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andAsctUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("asct_unit_id not between", value1, value2, "asctUnitId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdIsNull() {
            addCriterion("cpny_acst_rep_id is null");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdIsNotNull() {
            addCriterion("cpny_acst_rep_id is not null");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdEqualTo(Integer value) {
            addCriterion("cpny_acst_rep_id =", value, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdNotEqualTo(Integer value) {
            addCriterion("cpny_acst_rep_id <>", value, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdGreaterThan(Integer value) {
            addCriterion("cpny_acst_rep_id >", value, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpny_acst_rep_id >=", value, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdLessThan(Integer value) {
            addCriterion("cpny_acst_rep_id <", value, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdLessThanOrEqualTo(Integer value) {
            addCriterion("cpny_acst_rep_id <=", value, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdIn(List<Integer> values) {
            addCriterion("cpny_acst_rep_id in", values, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdNotIn(List<Integer> values) {
            addCriterion("cpny_acst_rep_id not in", values, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdBetween(Integer value1, Integer value2) {
            addCriterion("cpny_acst_rep_id between", value1, value2, "cpnyAcstRepId");
            return (Criteria) this;
        }

        public Criteria andCpnyAcstRepIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cpny_acst_rep_id not between", value1, value2, "cpnyAcstRepId");
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