package com.exam.model;

import java.util.ArrayList;
import java.util.List;

public class ExamStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamStudentExample() {
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

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Integer value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Integer value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Integer value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Integer value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Integer> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Integer> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNull() {
            addCriterion("student_name is null");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNotNull() {
            addCriterion("student_name is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNameEqualTo(String value) {
            addCriterion("student_name =", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotEqualTo(String value) {
            addCriterion("student_name <>", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThan(String value) {
            addCriterion("student_name >", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThanOrEqualTo(String value) {
            addCriterion("student_name >=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThan(String value) {
            addCriterion("student_name <", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThanOrEqualTo(String value) {
            addCriterion("student_name <=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLike(String value) {
            addCriterion("student_name like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotLike(String value) {
            addCriterion("student_name not like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameIn(List<String> values) {
            addCriterion("student_name in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotIn(List<String> values) {
            addCriterion("student_name not in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameBetween(String value1, String value2) {
            addCriterion("student_name between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotBetween(String value1, String value2) {
            addCriterion("student_name not between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentEmailIsNull() {
            addCriterion("student_email is null");
            return (Criteria) this;
        }

        public Criteria andStudentEmailIsNotNull() {
            addCriterion("student_email is not null");
            return (Criteria) this;
        }

        public Criteria andStudentEmailEqualTo(String value) {
            addCriterion("student_email =", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotEqualTo(String value) {
            addCriterion("student_email <>", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailGreaterThan(String value) {
            addCriterion("student_email >", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailGreaterThanOrEqualTo(String value) {
            addCriterion("student_email >=", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailLessThan(String value) {
            addCriterion("student_email <", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailLessThanOrEqualTo(String value) {
            addCriterion("student_email <=", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailLike(String value) {
            addCriterion("student_email like", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotLike(String value) {
            addCriterion("student_email not like", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailIn(List<String> values) {
            addCriterion("student_email in", values, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotIn(List<String> values) {
            addCriterion("student_email not in", values, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailBetween(String value1, String value2) {
            addCriterion("student_email between", value1, value2, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotBetween(String value1, String value2) {
            addCriterion("student_email not between", value1, value2, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentGradeIsNull() {
            addCriterion("student_grade is null");
            return (Criteria) this;
        }

        public Criteria andStudentGradeIsNotNull() {
            addCriterion("student_grade is not null");
            return (Criteria) this;
        }

        public Criteria andStudentGradeEqualTo(Integer value) {
            addCriterion("student_grade =", value, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeNotEqualTo(Integer value) {
            addCriterion("student_grade <>", value, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeGreaterThan(Integer value) {
            addCriterion("student_grade >", value, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_grade >=", value, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeLessThan(Integer value) {
            addCriterion("student_grade <", value, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeLessThanOrEqualTo(Integer value) {
            addCriterion("student_grade <=", value, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeIn(List<Integer> values) {
            addCriterion("student_grade in", values, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeNotIn(List<Integer> values) {
            addCriterion("student_grade not in", values, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeBetween(Integer value1, Integer value2) {
            addCriterion("student_grade between", value1, value2, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("student_grade not between", value1, value2, "studentGrade");
            return (Criteria) this;
        }

        public Criteria andStudentClassIsNull() {
            addCriterion("student_class is null");
            return (Criteria) this;
        }

        public Criteria andStudentClassIsNotNull() {
            addCriterion("student_class is not null");
            return (Criteria) this;
        }

        public Criteria andStudentClassEqualTo(Integer value) {
            addCriterion("student_class =", value, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassNotEqualTo(Integer value) {
            addCriterion("student_class <>", value, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassGreaterThan(Integer value) {
            addCriterion("student_class >", value, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_class >=", value, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassLessThan(Integer value) {
            addCriterion("student_class <", value, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassLessThanOrEqualTo(Integer value) {
            addCriterion("student_class <=", value, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassIn(List<Integer> values) {
            addCriterion("student_class in", values, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassNotIn(List<Integer> values) {
            addCriterion("student_class not in", values, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassBetween(Integer value1, Integer value2) {
            addCriterion("student_class between", value1, value2, "studentClass");
            return (Criteria) this;
        }

        public Criteria andStudentClassNotBetween(Integer value1, Integer value2) {
            addCriterion("student_class not between", value1, value2, "studentClass");
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