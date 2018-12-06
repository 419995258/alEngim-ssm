package org.zyyd.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BaseBookVersionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseBookVersionExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andVersionIdIsNull() {
            addCriterion("VERSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("VERSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVersionIdEqualTo(String value) {
            addCriterion("VERSION_ID =", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotEqualTo(String value) {
            addCriterion("VERSION_ID <>", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThan(String value) {
            addCriterion("VERSION_ID >", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION_ID >=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThan(String value) {
            addCriterion("VERSION_ID <", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(String value) {
            addCriterion("VERSION_ID <=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLike(String value) {
            addCriterion("VERSION_ID like", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotLike(String value) {
            addCriterion("VERSION_ID not like", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdIn(List<String> values) {
            addCriterion("VERSION_ID in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotIn(List<String> values) {
            addCriterion("VERSION_ID not in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdBetween(String value1, String value2) {
            addCriterion("VERSION_ID between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotBetween(String value1, String value2) {
            addCriterion("VERSION_ID not between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIsNull() {
            addCriterion("SUBJECT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIsNotNull() {
            addCriterion("SUBJECT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeEqualTo(String value) {
            addCriterion("SUBJECT_CODE =", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotEqualTo(String value) {
            addCriterion("SUBJECT_CODE <>", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeGreaterThan(String value) {
            addCriterion("SUBJECT_CODE >", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_CODE >=", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLessThan(String value) {
            addCriterion("SUBJECT_CODE <", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_CODE <=", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLike(String value) {
            addCriterion("SUBJECT_CODE like", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotLike(String value) {
            addCriterion("SUBJECT_CODE not like", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIn(List<String> values) {
            addCriterion("SUBJECT_CODE in", values, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotIn(List<String> values) {
            addCriterion("SUBJECT_CODE not in", values, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeBetween(String value1, String value2) {
            addCriterion("SUBJECT_CODE between", value1, value2, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_CODE not between", value1, value2, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andVersionNameIsNull() {
            addCriterion("VERSION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVersionNameIsNotNull() {
            addCriterion("VERSION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNameEqualTo(String value) {
            addCriterion("VERSION_NAME =", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotEqualTo(String value) {
            addCriterion("VERSION_NAME <>", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameGreaterThan(String value) {
            addCriterion("VERSION_NAME >", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION_NAME >=", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameLessThan(String value) {
            addCriterion("VERSION_NAME <", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameLessThanOrEqualTo(String value) {
            addCriterion("VERSION_NAME <=", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameLike(String value) {
            addCriterion("VERSION_NAME like", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotLike(String value) {
            addCriterion("VERSION_NAME not like", value, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameIn(List<String> values) {
            addCriterion("VERSION_NAME in", values, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotIn(List<String> values) {
            addCriterion("VERSION_NAME not in", values, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameBetween(String value1, String value2) {
            addCriterion("VERSION_NAME between", value1, value2, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionNameNotBetween(String value1, String value2) {
            addCriterion("VERSION_NAME not between", value1, value2, "versionName");
            return (Criteria) this;
        }

        public Criteria andVersionCodeIsNull() {
            addCriterion("VERSION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeIsNotNull() {
            addCriterion("VERSION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVersionCodeEqualTo(String value) {
            addCriterion("VERSION_CODE =", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotEqualTo(String value) {
            addCriterion("VERSION_CODE <>", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeGreaterThan(String value) {
            addCriterion("VERSION_CODE >", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION_CODE >=", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeLessThan(String value) {
            addCriterion("VERSION_CODE <", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeLessThanOrEqualTo(String value) {
            addCriterion("VERSION_CODE <=", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeLike(String value) {
            addCriterion("VERSION_CODE like", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotLike(String value) {
            addCriterion("VERSION_CODE not like", value, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeIn(List<String> values) {
            addCriterion("VERSION_CODE in", values, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotIn(List<String> values) {
            addCriterion("VERSION_CODE not in", values, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeBetween(String value1, String value2) {
            addCriterion("VERSION_CODE between", value1, value2, "versionCode");
            return (Criteria) this;
        }

        public Criteria andVersionCodeNotBetween(String value1, String value2) {
            addCriterion("VERSION_CODE not between", value1, value2, "versionCode");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNull() {
            addCriterion("SEQ_NO is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("SEQ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(Integer value) {
            addCriterion("SEQ_NO =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(Integer value) {
            addCriterion("SEQ_NO <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(Integer value) {
            addCriterion("SEQ_NO >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("SEQ_NO >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(Integer value) {
            addCriterion("SEQ_NO <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("SEQ_NO <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<Integer> values) {
            addCriterion("SEQ_NO in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<Integer> values) {
            addCriterion("SEQ_NO not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("SEQ_NO between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("SEQ_NO not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andCreUserIsNull() {
            addCriterion("CRE_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreUserIsNotNull() {
            addCriterion("CRE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreUserEqualTo(String value) {
            addCriterion("CRE_USER =", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserNotEqualTo(String value) {
            addCriterion("CRE_USER <>", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserGreaterThan(String value) {
            addCriterion("CRE_USER >", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserGreaterThanOrEqualTo(String value) {
            addCriterion("CRE_USER >=", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserLessThan(String value) {
            addCriterion("CRE_USER <", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserLessThanOrEqualTo(String value) {
            addCriterion("CRE_USER <=", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserLike(String value) {
            addCriterion("CRE_USER like", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserNotLike(String value) {
            addCriterion("CRE_USER not like", value, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserIn(List<String> values) {
            addCriterion("CRE_USER in", values, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserNotIn(List<String> values) {
            addCriterion("CRE_USER not in", values, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserBetween(String value1, String value2) {
            addCriterion("CRE_USER between", value1, value2, "creUser");
            return (Criteria) this;
        }

        public Criteria andCreUserNotBetween(String value1, String value2) {
            addCriterion("CRE_USER not between", value1, value2, "creUser");
            return (Criteria) this;
        }

        public Criteria andModUserIsNull() {
            addCriterion("MOD_USER is null");
            return (Criteria) this;
        }

        public Criteria andModUserIsNotNull() {
            addCriterion("MOD_USER is not null");
            return (Criteria) this;
        }

        public Criteria andModUserEqualTo(String value) {
            addCriterion("MOD_USER =", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserNotEqualTo(String value) {
            addCriterion("MOD_USER <>", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserGreaterThan(String value) {
            addCriterion("MOD_USER >", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserGreaterThanOrEqualTo(String value) {
            addCriterion("MOD_USER >=", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserLessThan(String value) {
            addCriterion("MOD_USER <", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserLessThanOrEqualTo(String value) {
            addCriterion("MOD_USER <=", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserLike(String value) {
            addCriterion("MOD_USER like", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserNotLike(String value) {
            addCriterion("MOD_USER not like", value, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserIn(List<String> values) {
            addCriterion("MOD_USER in", values, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserNotIn(List<String> values) {
            addCriterion("MOD_USER not in", values, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserBetween(String value1, String value2) {
            addCriterion("MOD_USER between", value1, value2, "modUser");
            return (Criteria) this;
        }

        public Criteria andModUserNotBetween(String value1, String value2) {
            addCriterion("MOD_USER not between", value1, value2, "modUser");
            return (Criteria) this;
        }

        public Criteria andCreTimeIsNull() {
            addCriterion("CRE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreTimeIsNotNull() {
            addCriterion("CRE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_TIME =", value, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_TIME <>", value, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CRE_TIME >", value, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_TIME >=", value, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeLessThan(Date value) {
            addCriterionForJDBCDate("CRE_TIME <", value, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CRE_TIME <=", value, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CRE_TIME in", values, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CRE_TIME not in", values, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CRE_TIME between", value1, value2, "creTime");
            return (Criteria) this;
        }

        public Criteria andCreTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CRE_TIME not between", value1, value2, "creTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNull() {
            addCriterion("MOD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNotNull() {
            addCriterion("MOD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModTimeEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_TIME =", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_TIME <>", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("MOD_TIME >", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_TIME >=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThan(Date value) {
            addCriterionForJDBCDate("MOD_TIME <", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("MOD_TIME <=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIn(List<Date> values) {
            addCriterionForJDBCDate("MOD_TIME in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("MOD_TIME not in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MOD_TIME between", value1, value2, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("MOD_TIME not between", value1, value2, "modTime");
            return (Criteria) this;
        }

        public Criteria andAbbreviationIsNull() {
            addCriterion("ABBREVIATION is null");
            return (Criteria) this;
        }

        public Criteria andAbbreviationIsNotNull() {
            addCriterion("ABBREVIATION is not null");
            return (Criteria) this;
        }

        public Criteria andAbbreviationEqualTo(String value) {
            addCriterion("ABBREVIATION =", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotEqualTo(String value) {
            addCriterion("ABBREVIATION <>", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationGreaterThan(String value) {
            addCriterion("ABBREVIATION >", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationGreaterThanOrEqualTo(String value) {
            addCriterion("ABBREVIATION >=", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationLessThan(String value) {
            addCriterion("ABBREVIATION <", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationLessThanOrEqualTo(String value) {
            addCriterion("ABBREVIATION <=", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationLike(String value) {
            addCriterion("ABBREVIATION like", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotLike(String value) {
            addCriterion("ABBREVIATION not like", value, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationIn(List<String> values) {
            addCriterion("ABBREVIATION in", values, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotIn(List<String> values) {
            addCriterion("ABBREVIATION not in", values, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationBetween(String value1, String value2) {
            addCriterion("ABBREVIATION between", value1, value2, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andAbbreviationNotBetween(String value1, String value2) {
            addCriterion("ABBREVIATION not between", value1, value2, "abbreviation");
            return (Criteria) this;
        }

        public Criteria andPressIsNull() {
            addCriterion("PRESS is null");
            return (Criteria) this;
        }

        public Criteria andPressIsNotNull() {
            addCriterion("PRESS is not null");
            return (Criteria) this;
        }

        public Criteria andPressEqualTo(String value) {
            addCriterion("PRESS =", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotEqualTo(String value) {
            addCriterion("PRESS <>", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressGreaterThan(String value) {
            addCriterion("PRESS >", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressGreaterThanOrEqualTo(String value) {
            addCriterion("PRESS >=", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLessThan(String value) {
            addCriterion("PRESS <", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLessThanOrEqualTo(String value) {
            addCriterion("PRESS <=", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLike(String value) {
            addCriterion("PRESS like", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotLike(String value) {
            addCriterion("PRESS not like", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressIn(List<String> values) {
            addCriterion("PRESS in", values, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotIn(List<String> values) {
            addCriterion("PRESS not in", values, "press");
            return (Criteria) this;
        }

        public Criteria andPressBetween(String value1, String value2) {
            addCriterion("PRESS between", value1, value2, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotBetween(String value1, String value2) {
            addCriterion("PRESS not between", value1, value2, "press");
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