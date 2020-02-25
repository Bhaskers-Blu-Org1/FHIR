/*
 * (C) Copyright IBM Corp. 2019, 2020
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.model.type.code;

import com.ibm.fhir.model.annotation.System;
import com.ibm.fhir.model.type.Code;
import com.ibm.fhir.model.type.Extension;
import com.ibm.fhir.model.type.String;

import java.util.Collection;
import java.util.Objects;

import javax.annotation.Generated;

@System("http://hl7.org/fhir/care-plan-activity-status")
@Generated("com.ibm.fhir.tools.CodeGenerator")
public class CarePlanActivityStatus extends Code {
    /**
     * Not Started
     */
    public static final CarePlanActivityStatus NOT_STARTED = CarePlanActivityStatus.builder().value(ValueSet.NOT_STARTED).build();

    /**
     * Scheduled
     */
    public static final CarePlanActivityStatus SCHEDULED = CarePlanActivityStatus.builder().value(ValueSet.SCHEDULED).build();

    /**
     * In Progress
     */
    public static final CarePlanActivityStatus IN_PROGRESS = CarePlanActivityStatus.builder().value(ValueSet.IN_PROGRESS).build();

    /**
     * On Hold
     */
    public static final CarePlanActivityStatus ON_HOLD = CarePlanActivityStatus.builder().value(ValueSet.ON_HOLD).build();

    /**
     * Completed
     */
    public static final CarePlanActivityStatus COMPLETED = CarePlanActivityStatus.builder().value(ValueSet.COMPLETED).build();

    /**
     * Cancelled
     */
    public static final CarePlanActivityStatus CANCELLED = CarePlanActivityStatus.builder().value(ValueSet.CANCELLED).build();

    /**
     * Stopped
     */
    public static final CarePlanActivityStatus STOPPED = CarePlanActivityStatus.builder().value(ValueSet.STOPPED).build();

    /**
     * Unknown
     */
    public static final CarePlanActivityStatus UNKNOWN = CarePlanActivityStatus.builder().value(ValueSet.UNKNOWN).build();

    /**
     * Entered in Error
     */
    public static final CarePlanActivityStatus ENTERED_IN_ERROR = CarePlanActivityStatus.builder().value(ValueSet.ENTERED_IN_ERROR).build();

    private volatile int hashCode;

    private CarePlanActivityStatus(Builder builder) {
        super(builder);
    }

    public static CarePlanActivityStatus of(ValueSet value) {
        switch (value) {
        case NOT_STARTED:
            return NOT_STARTED;
        case SCHEDULED:
            return SCHEDULED;
        case IN_PROGRESS:
            return IN_PROGRESS;
        case ON_HOLD:
            return ON_HOLD;
        case COMPLETED:
            return COMPLETED;
        case CANCELLED:
            return CANCELLED;
        case STOPPED:
            return STOPPED;
        case UNKNOWN:
            return UNKNOWN;
        case ENTERED_IN_ERROR:
            return ENTERED_IN_ERROR;
        default:
            throw new IllegalStateException(value.name());
        }
    }

    public static CarePlanActivityStatus of(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    public static String string(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    public static Code code(java.lang.String value) {
        return of(ValueSet.from(value));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CarePlanActivityStatus other = (CarePlanActivityStatus) obj;
        return Objects.equals(id, other.id) && Objects.equals(extension, other.extension) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Objects.hash(id, extension, value);
            hashCode = result;
        }
        return result;
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.id(id);
        builder.extension(extension);
        builder.value(value);
        return builder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Code.Builder {
        private Builder() {
            super();
        }

        @Override
        public Builder id(java.lang.String id) {
            return (Builder) super.id(id);
        }

        @Override
        public Builder extension(Extension... extension) {
            return (Builder) super.extension(extension);
        }

        @Override
        public Builder extension(Collection<Extension> extension) {
            return (Builder) super.extension(extension);
        }

        @Override
        public Builder value(java.lang.String value) {
            return (value != null) ? (Builder) super.value(ValueSet.from(value).value()) : this;
        }

        public Builder value(ValueSet value) {
            return (value != null) ? (Builder) super.value(value.value()) : this;
        }

        @Override
        public CarePlanActivityStatus build() {
            return new CarePlanActivityStatus(this);
        }
    }

    public enum ValueSet {
        /**
         * Not Started
         */
        NOT_STARTED("not-started"),

        /**
         * Scheduled
         */
        SCHEDULED("scheduled"),

        /**
         * In Progress
         */
        IN_PROGRESS("in-progress"),

        /**
         * On Hold
         */
        ON_HOLD("on-hold"),

        /**
         * Completed
         */
        COMPLETED("completed"),

        /**
         * Cancelled
         */
        CANCELLED("cancelled"),

        /**
         * Stopped
         */
        STOPPED("stopped"),

        /**
         * Unknown
         */
        UNKNOWN("unknown"),

        /**
         * Entered in Error
         */
        ENTERED_IN_ERROR("entered-in-error");

        private final java.lang.String value;

        ValueSet(java.lang.String value) {
            this.value = value;
        }

        public java.lang.String value() {
            return value;
        }

        public static ValueSet from(java.lang.String value) {
            for (ValueSet c : ValueSet.values()) {
                if (c.value.equals(value)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }
}