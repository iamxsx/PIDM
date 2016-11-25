package com.oneclouder.model;

import java.util.ArrayList;
import java.util.List;

public class TCompanyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCompanyExample() {
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

        public Criteria andNatureIsNull() {
            addCriterion("nature is null");
            return (Criteria) this;
        }

        public Criteria andNatureIsNotNull() {
            addCriterion("nature is not null");
            return (Criteria) this;
        }

        public Criteria andNatureEqualTo(String value) {
            addCriterion("nature =", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotEqualTo(String value) {
            addCriterion("nature <>", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThan(String value) {
            addCriterion("nature >", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThanOrEqualTo(String value) {
            addCriterion("nature >=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThan(String value) {
            addCriterion("nature <", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThanOrEqualTo(String value) {
            addCriterion("nature <=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLike(String value) {
            addCriterion("nature like", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotLike(String value) {
            addCriterion("nature not like", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureIn(List<String> values) {
            addCriterion("nature in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotIn(List<String> values) {
            addCriterion("nature not in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureBetween(String value1, String value2) {
            addCriterion("nature between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotBetween(String value1, String value2) {
            addCriterion("nature not between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("county like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("county not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("county not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNull() {
            addCriterion("zip_code is null");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNotNull() {
            addCriterion("zip_code is not null");
            return (Criteria) this;
        }

        public Criteria andZipCodeEqualTo(String value) {
            addCriterion("zip_code =", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotEqualTo(String value) {
            addCriterion("zip_code <>", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThan(String value) {
            addCriterion("zip_code >", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("zip_code >=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThan(String value) {
            addCriterion("zip_code <", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThanOrEqualTo(String value) {
            addCriterion("zip_code <=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLike(String value) {
            addCriterion("zip_code like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotLike(String value) {
            addCriterion("zip_code not like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeIn(List<String> values) {
            addCriterion("zip_code in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotIn(List<String> values) {
            addCriterion("zip_code not in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeBetween(String value1, String value2) {
            addCriterion("zip_code between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotBetween(String value1, String value2) {
            addCriterion("zip_code not between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andInternetSiteIsNull() {
            addCriterion("internet_site is null");
            return (Criteria) this;
        }

        public Criteria andInternetSiteIsNotNull() {
            addCriterion("internet_site is not null");
            return (Criteria) this;
        }

        public Criteria andInternetSiteEqualTo(String value) {
            addCriterion("internet_site =", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteNotEqualTo(String value) {
            addCriterion("internet_site <>", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteGreaterThan(String value) {
            addCriterion("internet_site >", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteGreaterThanOrEqualTo(String value) {
            addCriterion("internet_site >=", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteLessThan(String value) {
            addCriterion("internet_site <", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteLessThanOrEqualTo(String value) {
            addCriterion("internet_site <=", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteLike(String value) {
            addCriterion("internet_site like", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteNotLike(String value) {
            addCriterion("internet_site not like", value, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteIn(List<String> values) {
            addCriterion("internet_site in", values, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteNotIn(List<String> values) {
            addCriterion("internet_site not in", values, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteBetween(String value1, String value2) {
            addCriterion("internet_site between", value1, value2, "internetSite");
            return (Criteria) this;
        }

        public Criteria andInternetSiteNotBetween(String value1, String value2) {
            addCriterion("internet_site not between", value1, value2, "internetSite");
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

        public Criteria andRegisterNatureIsNull() {
            addCriterion("register_nature is null");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureIsNotNull() {
            addCriterion("register_nature is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureEqualTo(String value) {
            addCriterion("register_nature =", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureNotEqualTo(String value) {
            addCriterion("register_nature <>", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureGreaterThan(String value) {
            addCriterion("register_nature >", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureGreaterThanOrEqualTo(String value) {
            addCriterion("register_nature >=", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureLessThan(String value) {
            addCriterion("register_nature <", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureLessThanOrEqualTo(String value) {
            addCriterion("register_nature <=", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureLike(String value) {
            addCriterion("register_nature like", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureNotLike(String value) {
            addCriterion("register_nature not like", value, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureIn(List<String> values) {
            addCriterion("register_nature in", values, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureNotIn(List<String> values) {
            addCriterion("register_nature not in", values, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureBetween(String value1, String value2) {
            addCriterion("register_nature between", value1, value2, "registerNature");
            return (Criteria) this;
        }

        public Criteria andRegisterNatureNotBetween(String value1, String value2) {
            addCriterion("register_nature not between", value1, value2, "registerNature");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("industry is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("industry is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(String value) {
            addCriterion("industry =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("industry <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("industry >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("industry >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(String value) {
            addCriterion("industry <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("industry <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLike(String value) {
            addCriterion("industry like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotLike(String value) {
            addCriterion("industry not like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<String> values) {
            addCriterion("industry in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("industry not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("industry between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("industry not between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentIsNull() {
            addCriterion("admin_department is null");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentIsNotNull() {
            addCriterion("admin_department is not null");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentEqualTo(String value) {
            addCriterion("admin_department =", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentNotEqualTo(String value) {
            addCriterion("admin_department <>", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentGreaterThan(String value) {
            addCriterion("admin_department >", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("admin_department >=", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentLessThan(String value) {
            addCriterion("admin_department <", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentLessThanOrEqualTo(String value) {
            addCriterion("admin_department <=", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentLike(String value) {
            addCriterion("admin_department like", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentNotLike(String value) {
            addCriterion("admin_department not like", value, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentIn(List<String> values) {
            addCriterion("admin_department in", values, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentNotIn(List<String> values) {
            addCriterion("admin_department not in", values, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentBetween(String value1, String value2) {
            addCriterion("admin_department between", value1, value2, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminDepartmentNotBetween(String value1, String value2) {
            addCriterion("admin_department not between", value1, value2, "adminDepartment");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIsNull() {
            addCriterion("employee_num is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIsNotNull() {
            addCriterion("employee_num is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumEqualTo(Integer value) {
            addCriterion("employee_num =", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotEqualTo(Integer value) {
            addCriterion("employee_num <>", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumGreaterThan(Integer value) {
            addCriterion("employee_num >", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee_num >=", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLessThan(Integer value) {
            addCriterion("employee_num <", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLessThanOrEqualTo(Integer value) {
            addCriterion("employee_num <=", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIn(List<Integer> values) {
            addCriterion("employee_num in", values, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotIn(List<Integer> values) {
            addCriterion("employee_num not in", values, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumBetween(Integer value1, Integer value2) {
            addCriterion("employee_num between", value1, value2, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("employee_num not between", value1, value2, "employeeNum");
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