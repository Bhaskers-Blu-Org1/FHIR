/*
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.model.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;

import com.ibm.fhir.model.annotation.Binding;
import com.ibm.fhir.model.annotation.Choice;
import com.ibm.fhir.model.annotation.Constraint;
import com.ibm.fhir.model.annotation.Required;
import com.ibm.fhir.model.type.Annotation;
import com.ibm.fhir.model.type.BackboneElement;
import com.ibm.fhir.model.type.Code;
import com.ibm.fhir.model.type.CodeableConcept;
import com.ibm.fhir.model.type.DateTime;
import com.ibm.fhir.model.type.Decimal;
import com.ibm.fhir.model.type.Element;
import com.ibm.fhir.model.type.Extension;
import com.ibm.fhir.model.type.Id;
import com.ibm.fhir.model.type.Identifier;
import com.ibm.fhir.model.type.Meta;
import com.ibm.fhir.model.type.Narrative;
import com.ibm.fhir.model.type.Period;
import com.ibm.fhir.model.type.Range;
import com.ibm.fhir.model.type.Reference;
import com.ibm.fhir.model.type.String;
import com.ibm.fhir.model.type.Uri;
import com.ibm.fhir.model.type.code.BindingStrength;
import com.ibm.fhir.model.type.code.RiskAssessmentStatus;
import com.ibm.fhir.model.util.ValidationSupport;
import com.ibm.fhir.model.visitor.Visitor;

/**
 * An assessment of the likely outcome(s) for a patient or other subject as well as the likelihood of each outcome.
 */
@Constraint(
    id = "ras-1",
    level = "Rule",
    location = "RiskAssessment.prediction.probability",
    description = "low and high must be percentages, if present",
    expression = "(low.empty() or ((low.code = '%') and (low.system = %ucum))) and (high.empty() or ((high.code = '%') and (high.system = %ucum)))"
)
@Constraint(
    id = "ras-2",
    level = "Rule",
    location = "RiskAssessment.prediction",
    description = "Must be <= 100",
    expression = "probability is decimal implies (probability as decimal) <= 100"
)
@Generated("com.ibm.fhir.tools.CodeGenerator")
public class RiskAssessment extends DomainResource {
    private final List<Identifier> identifier;
    private final Reference basedOn;
    private final Reference parent;
    @Required
    @Binding(
        bindingName = "RiskAssessmentStatus",
        strength = BindingStrength.ValueSet.REQUIRED,
        description = "The status of the risk assessment; e.g. preliminary, final, amended, etc.",
        valueSet = "http://hl7.org/fhir/ValueSet/observation-status|4.0.0"
    )
    private final RiskAssessmentStatus status;
    @Binding(
        bindingName = "RiskAssessmentMethod",
        strength = BindingStrength.ValueSet.EXAMPLE,
        description = "The mechanism or algorithm used to make the assessment; e.g. TIMI, PRISM, Cardiff Type 2 diabetes, etc."
    )
    private final CodeableConcept method;
    private final CodeableConcept code;
    @Required
    private final Reference subject;
    private final Reference encounter;
    @Choice({ DateTime.class, Period.class })
    private final Element occurrence;
    private final Reference condition;
    private final Reference performer;
    private final List<CodeableConcept> reasonCode;
    private final List<Reference> reasonReference;
    private final List<Reference> basis;
    private final List<Prediction> prediction;
    private final String mitigation;
    private final List<Annotation> note;

    private volatile int hashCode;

    private RiskAssessment(Builder builder) {
        super(builder);
        identifier = Collections.unmodifiableList(ValidationSupport.requireNonNull(builder.identifier, "identifier"));
        basedOn = builder.basedOn;
        parent = builder.parent;
        status = ValidationSupport.requireNonNull(builder.status, "status");
        method = builder.method;
        code = builder.code;
        subject = ValidationSupport.requireNonNull(builder.subject, "subject");
        encounter = builder.encounter;
        occurrence = ValidationSupport.choiceElement(builder.occurrence, "occurrence", DateTime.class, Period.class);
        condition = builder.condition;
        performer = builder.performer;
        reasonCode = Collections.unmodifiableList(ValidationSupport.requireNonNull(builder.reasonCode, "reasonCode"));
        reasonReference = Collections.unmodifiableList(ValidationSupport.requireNonNull(builder.reasonReference, "reasonReference"));
        basis = Collections.unmodifiableList(ValidationSupport.requireNonNull(builder.basis, "basis"));
        prediction = Collections.unmodifiableList(ValidationSupport.requireNonNull(builder.prediction, "prediction"));
        mitigation = builder.mitigation;
        note = Collections.unmodifiableList(ValidationSupport.requireNonNull(builder.note, "note"));
        ValidationSupport.requireChildren(this);
    }

    /**
     * Business identifier assigned to the risk assessment.
     * 
     * @return
     *     An unmodifiable list containing immutable objects of type {@link Identifier}.
     */
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    /**
     * A reference to the request that is fulfilled by this risk assessment.
     * 
     * @return
     *     An immutable object of type {@link Reference}.
     */
    public Reference getBasedOn() {
        return basedOn;
    }

    /**
     * A reference to a resource that this risk assessment is part of, such as a Procedure.
     * 
     * @return
     *     An immutable object of type {@link Reference}.
     */
    public Reference getParent() {
        return parent;
    }

    /**
     * The status of the RiskAssessment, using the same statuses as an Observation.
     * 
     * @return
     *     An immutable object of type {@link RiskAssessmentStatus}.
     */
    public RiskAssessmentStatus getStatus() {
        return status;
    }

    /**
     * The algorithm, process or mechanism used to evaluate the risk.
     * 
     * @return
     *     An immutable object of type {@link CodeableConcept}.
     */
    public CodeableConcept getMethod() {
        return method;
    }

    /**
     * The type of the risk assessment performed.
     * 
     * @return
     *     An immutable object of type {@link CodeableConcept}.
     */
    public CodeableConcept getCode() {
        return code;
    }

    /**
     * The patient or group the risk assessment applies to.
     * 
     * @return
     *     An immutable object of type {@link Reference}.
     */
    public Reference getSubject() {
        return subject;
    }

    /**
     * The encounter where the assessment was performed.
     * 
     * @return
     *     An immutable object of type {@link Reference}.
     */
    public Reference getEncounter() {
        return encounter;
    }

    /**
     * The date (and possibly time) the risk assessment was performed.
     * 
     * @return
     *     An immutable object of type {@link Element}.
     */
    public Element getOccurrence() {
        return occurrence;
    }

    /**
     * For assessments or prognosis specific to a particular condition, indicates the condition being assessed.
     * 
     * @return
     *     An immutable object of type {@link Reference}.
     */
    public Reference getCondition() {
        return condition;
    }

    /**
     * The provider or software application that performed the assessment.
     * 
     * @return
     *     An immutable object of type {@link Reference}.
     */
    public Reference getPerformer() {
        return performer;
    }

    /**
     * The reason the risk assessment was performed.
     * 
     * @return
     *     An unmodifiable list containing immutable objects of type {@link CodeableConcept}.
     */
    public List<CodeableConcept> getReasonCode() {
        return reasonCode;
    }

    /**
     * Resources supporting the reason the risk assessment was performed.
     * 
     * @return
     *     An unmodifiable list containing immutable objects of type {@link Reference}.
     */
    public List<Reference> getReasonReference() {
        return reasonReference;
    }

    /**
     * Indicates the source data considered as part of the assessment (for example, FamilyHistory, Observations, Procedures, 
     * Conditions, etc.).
     * 
     * @return
     *     An unmodifiable list containing immutable objects of type {@link Reference}.
     */
    public List<Reference> getBasis() {
        return basis;
    }

    /**
     * Describes the expected outcome for the subject.
     * 
     * @return
     *     An unmodifiable list containing immutable objects of type {@link Prediction}.
     */
    public List<Prediction> getPrediction() {
        return prediction;
    }

    /**
     * A description of the steps that might be taken to reduce the identified risk(s).
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getMitigation() {
        return mitigation;
    }

    /**
     * Additional comments about the risk assessment.
     * 
     * @return
     *     An unmodifiable list containing immutable objects of type {@link Annotation}.
     */
    public List<Annotation> getNote() {
        return note;
    }

    @Override
    public boolean hasChildren() {
        return super.hasChildren() || 
            !identifier.isEmpty() || 
            (basedOn != null) || 
            (parent != null) || 
            (status != null) || 
            (method != null) || 
            (code != null) || 
            (subject != null) || 
            (encounter != null) || 
            (occurrence != null) || 
            (condition != null) || 
            (performer != null) || 
            !reasonCode.isEmpty() || 
            !reasonReference.isEmpty() || 
            !basis.isEmpty() || 
            !prediction.isEmpty() || 
            (mitigation != null) || 
            !note.isEmpty();
    }

    @Override
    public void accept(java.lang.String elementName, int elementIndex, Visitor visitor) {
        if (visitor.preVisit(this)) {
            visitor.visitStart(elementName, elementIndex, this);
            if (visitor.visit(elementName, elementIndex, this)) {
                // visit children
                accept(id, "id", visitor);
                accept(meta, "meta", visitor);
                accept(implicitRules, "implicitRules", visitor);
                accept(language, "language", visitor);
                accept(text, "text", visitor);
                accept(contained, "contained", visitor, Resource.class);
                accept(extension, "extension", visitor, Extension.class);
                accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                accept(identifier, "identifier", visitor, Identifier.class);
                accept(basedOn, "basedOn", visitor);
                accept(parent, "parent", visitor);
                accept(status, "status", visitor);
                accept(method, "method", visitor);
                accept(code, "code", visitor);
                accept(subject, "subject", visitor);
                accept(encounter, "encounter", visitor);
                accept(occurrence, "occurrence", visitor);
                accept(condition, "condition", visitor);
                accept(performer, "performer", visitor);
                accept(reasonCode, "reasonCode", visitor, CodeableConcept.class);
                accept(reasonReference, "reasonReference", visitor, Reference.class);
                accept(basis, "basis", visitor, Reference.class);
                accept(prediction, "prediction", visitor, Prediction.class);
                accept(mitigation, "mitigation", visitor);
                accept(note, "note", visitor, Annotation.class);
            }
            visitor.visitEnd(elementName, elementIndex, this);
            visitor.postVisit(this);
        }
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
        RiskAssessment other = (RiskAssessment) obj;
        return Objects.equals(id, other.id) && 
            Objects.equals(meta, other.meta) && 
            Objects.equals(implicitRules, other.implicitRules) && 
            Objects.equals(language, other.language) && 
            Objects.equals(text, other.text) && 
            Objects.equals(contained, other.contained) && 
            Objects.equals(extension, other.extension) && 
            Objects.equals(modifierExtension, other.modifierExtension) && 
            Objects.equals(identifier, other.identifier) && 
            Objects.equals(basedOn, other.basedOn) && 
            Objects.equals(parent, other.parent) && 
            Objects.equals(status, other.status) && 
            Objects.equals(method, other.method) && 
            Objects.equals(code, other.code) && 
            Objects.equals(subject, other.subject) && 
            Objects.equals(encounter, other.encounter) && 
            Objects.equals(occurrence, other.occurrence) && 
            Objects.equals(condition, other.condition) && 
            Objects.equals(performer, other.performer) && 
            Objects.equals(reasonCode, other.reasonCode) && 
            Objects.equals(reasonReference, other.reasonReference) && 
            Objects.equals(basis, other.basis) && 
            Objects.equals(prediction, other.prediction) && 
            Objects.equals(mitigation, other.mitigation) && 
            Objects.equals(note, other.note);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Objects.hash(id, 
                meta, 
                implicitRules, 
                language, 
                text, 
                contained, 
                extension, 
                modifierExtension, 
                identifier, 
                basedOn, 
                parent, 
                status, 
                method, 
                code, 
                subject, 
                encounter, 
                occurrence, 
                condition, 
                performer, 
                reasonCode, 
                reasonReference, 
                basis, 
                prediction, 
                mitigation, 
                note);
            hashCode = result;
        }
        return result;
    }

    @Override
    public Builder toBuilder() {
        return new Builder().from(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends DomainResource.Builder {
        private List<Identifier> identifier = new ArrayList<>();
        private Reference basedOn;
        private Reference parent;
        private RiskAssessmentStatus status;
        private CodeableConcept method;
        private CodeableConcept code;
        private Reference subject;
        private Reference encounter;
        private Element occurrence;
        private Reference condition;
        private Reference performer;
        private List<CodeableConcept> reasonCode = new ArrayList<>();
        private List<Reference> reasonReference = new ArrayList<>();
        private List<Reference> basis = new ArrayList<>();
        private List<Prediction> prediction = new ArrayList<>();
        private String mitigation;
        private List<Annotation> note = new ArrayList<>();

        private Builder() {
            super();
        }

        /**
         * The logical id of the resource, as used in the URL for the resource. Once assigned, this value never changes.
         * 
         * @param id
         *     Logical id of this artifact
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder id(Id id) {
            return (Builder) super.id(id);
        }

        /**
         * The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content 
         * might not always be associated with version changes to the resource.
         * 
         * @param meta
         *     Metadata about the resource
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder meta(Meta meta) {
            return (Builder) super.meta(meta);
        }

        /**
         * A reference to a set of rules that were followed when the resource was constructed, and which must be understood when 
         * processing the content. Often, this is a reference to an implementation guide that defines the special rules along 
         * with other profiles etc.
         * 
         * @param implicitRules
         *     A set of rules under which this content was created
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder implicitRules(Uri implicitRules) {
            return (Builder) super.implicitRules(implicitRules);
        }

        /**
         * The base language in which the resource is written.
         * 
         * @param language
         *     Language of the resource content
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder language(Code language) {
            return (Builder) super.language(language);
        }

        /**
         * A human-readable narrative that contains a summary of the resource and can be used to represent the content of the 
         * resource to a human. The narrative need not encode all the structured data, but is required to contain sufficient 
         * detail to make it "clinically safe" for a human to just read the narrative. Resource definitions may define what 
         * content should be represented in the narrative to ensure clinical safety.
         * 
         * @param text
         *     Text summary of the resource, for human interpretation
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder text(Narrative text) {
            return (Builder) super.text(text);
        }

        /**
         * These resources do not have an independent existence apart from the resource that contains them - they cannot be 
         * identified independently, and nor can they have their own independent transaction scope.
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param contained
         *     Contained, inline Resources
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder contained(Resource... contained) {
            return (Builder) super.contained(contained);
        }

        /**
         * These resources do not have an independent existence apart from the resource that contains them - they cannot be 
         * identified independently, and nor can they have their own independent transaction scope.
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param contained
         *     Contained, inline Resources
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder contained(Collection<Resource> contained) {
            return (Builder) super.contained(contained);
        }

        /**
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder extension(Extension... extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder extension(Collection<Extension> extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * May be used to represent additional information that is not part of the basic definition of the resource and that 
         * modifies the understanding of the element that contains it and/or the understanding of the containing element's 
         * descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and 
         * manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
         * implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the 
         * definition of the extension. Applications processing a resource are required to check for modifier extensions.
         * 
         * <p>Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
         * change the meaning of modifierExtension itself).
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param modifierExtension
         *     Extensions that cannot be ignored
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder modifierExtension(Extension... modifierExtension) {
            return (Builder) super.modifierExtension(modifierExtension);
        }

        /**
         * May be used to represent additional information that is not part of the basic definition of the resource and that 
         * modifies the understanding of the element that contains it and/or the understanding of the containing element's 
         * descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and 
         * manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
         * implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the 
         * definition of the extension. Applications processing a resource are required to check for modifier extensions.
         * 
         * <p>Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
         * change the meaning of modifierExtension itself).
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param modifierExtension
         *     Extensions that cannot be ignored
         * 
         * @return
         *     A reference to this Builder instance
         */
        @Override
        public Builder modifierExtension(Collection<Extension> modifierExtension) {
            return (Builder) super.modifierExtension(modifierExtension);
        }

        /**
         * Business identifier assigned to the risk assessment.
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param identifier
         *     Unique identifier for the assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder identifier(Identifier... identifier) {
            for (Identifier value : identifier) {
                this.identifier.add(value);
            }
            return this;
        }

        /**
         * Business identifier assigned to the risk assessment.
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param identifier
         *     Unique identifier for the assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder identifier(Collection<Identifier> identifier) {
            this.identifier = new ArrayList<>(identifier);
            return this;
        }

        /**
         * A reference to the request that is fulfilled by this risk assessment.
         * 
         * @param basedOn
         *     Request fulfilled by this assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder basedOn(Reference basedOn) {
            this.basedOn = basedOn;
            return this;
        }

        /**
         * A reference to a resource that this risk assessment is part of, such as a Procedure.
         * 
         * @param parent
         *     Part of this occurrence
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder parent(Reference parent) {
            this.parent = parent;
            return this;
        }

        /**
         * The status of the RiskAssessment, using the same statuses as an Observation.
         * 
         * <p>This element is required.
         * 
         * @param status
         *     registered | preliminary | final | amended +
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder status(RiskAssessmentStatus status) {
            this.status = status;
            return this;
        }

        /**
         * The algorithm, process or mechanism used to evaluate the risk.
         * 
         * @param method
         *     Evaluation mechanism
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder method(CodeableConcept method) {
            this.method = method;
            return this;
        }

        /**
         * The type of the risk assessment performed.
         * 
         * @param code
         *     Type of assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder code(CodeableConcept code) {
            this.code = code;
            return this;
        }

        /**
         * The patient or group the risk assessment applies to.
         * 
         * <p>This element is required.
         * 
         * @param subject
         *     Who/what does assessment apply to?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder subject(Reference subject) {
            this.subject = subject;
            return this;
        }

        /**
         * The encounter where the assessment was performed.
         * 
         * @param encounter
         *     Where was assessment performed?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder encounter(Reference encounter) {
            this.encounter = encounter;
            return this;
        }

        /**
         * The date (and possibly time) the risk assessment was performed.
         * 
         * <p>This is a choice element with the following allowed types:
         * <ul>
         * <li>{@link DateTime}</li>
         * <li>{@link Period}</li>
         * </ul>
         * 
         * @param occurrence
         *     When was assessment made?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder occurrence(Element occurrence) {
            this.occurrence = occurrence;
            return this;
        }

        /**
         * For assessments or prognosis specific to a particular condition, indicates the condition being assessed.
         * 
         * @param condition
         *     Condition assessed
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder condition(Reference condition) {
            this.condition = condition;
            return this;
        }

        /**
         * The provider or software application that performed the assessment.
         * 
         * @param performer
         *     Who did assessment?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder performer(Reference performer) {
            this.performer = performer;
            return this;
        }

        /**
         * The reason the risk assessment was performed.
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param reasonCode
         *     Why the assessment was necessary?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder reasonCode(CodeableConcept... reasonCode) {
            for (CodeableConcept value : reasonCode) {
                this.reasonCode.add(value);
            }
            return this;
        }

        /**
         * The reason the risk assessment was performed.
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param reasonCode
         *     Why the assessment was necessary?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder reasonCode(Collection<CodeableConcept> reasonCode) {
            this.reasonCode = new ArrayList<>(reasonCode);
            return this;
        }

        /**
         * Resources supporting the reason the risk assessment was performed.
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param reasonReference
         *     Why the assessment was necessary?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder reasonReference(Reference... reasonReference) {
            for (Reference value : reasonReference) {
                this.reasonReference.add(value);
            }
            return this;
        }

        /**
         * Resources supporting the reason the risk assessment was performed.
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param reasonReference
         *     Why the assessment was necessary?
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder reasonReference(Collection<Reference> reasonReference) {
            this.reasonReference = new ArrayList<>(reasonReference);
            return this;
        }

        /**
         * Indicates the source data considered as part of the assessment (for example, FamilyHistory, Observations, Procedures, 
         * Conditions, etc.).
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param basis
         *     Information used in assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder basis(Reference... basis) {
            for (Reference value : basis) {
                this.basis.add(value);
            }
            return this;
        }

        /**
         * Indicates the source data considered as part of the assessment (for example, FamilyHistory, Observations, Procedures, 
         * Conditions, etc.).
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param basis
         *     Information used in assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder basis(Collection<Reference> basis) {
            this.basis = new ArrayList<>(basis);
            return this;
        }

        /**
         * Describes the expected outcome for the subject.
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param prediction
         *     Outcome predicted
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder prediction(Prediction... prediction) {
            for (Prediction value : prediction) {
                this.prediction.add(value);
            }
            return this;
        }

        /**
         * Describes the expected outcome for the subject.
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param prediction
         *     Outcome predicted
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder prediction(Collection<Prediction> prediction) {
            this.prediction = new ArrayList<>(prediction);
            return this;
        }

        /**
         * A description of the steps that might be taken to reduce the identified risk(s).
         * 
         * @param mitigation
         *     How to reduce risk
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder mitigation(String mitigation) {
            this.mitigation = mitigation;
            return this;
        }

        /**
         * Additional comments about the risk assessment.
         * 
         * <p>Adds new element(s) to the existing list
         * 
         * @param note
         *     Comments on the risk assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder note(Annotation... note) {
            for (Annotation value : note) {
                this.note.add(value);
            }
            return this;
        }

        /**
         * Additional comments about the risk assessment.
         * 
         * <p>Replaces the existing list with a new one containing elements from the Collection
         * 
         * @param note
         *     Comments on the risk assessment
         * 
         * @return
         *     A reference to this Builder instance
         */
        public Builder note(Collection<Annotation> note) {
            this.note = new ArrayList<>(note);
            return this;
        }

        /**
         * Build the {@link RiskAssessment}
         * 
         * <p>Required elements:
         * <ul>
         * <li>status</li>
         * <li>subject</li>
         * </ul>
         * 
         * @return
         *     An immutable object of type {@link RiskAssessment}
         */
        @Override
        public RiskAssessment build() {
            return new RiskAssessment(this);
        }

        protected Builder from(RiskAssessment riskAssessment) {
            super.from(riskAssessment);
            identifier.addAll(riskAssessment.identifier);
            basedOn = riskAssessment.basedOn;
            parent = riskAssessment.parent;
            status = riskAssessment.status;
            method = riskAssessment.method;
            code = riskAssessment.code;
            subject = riskAssessment.subject;
            encounter = riskAssessment.encounter;
            occurrence = riskAssessment.occurrence;
            condition = riskAssessment.condition;
            performer = riskAssessment.performer;
            reasonCode.addAll(riskAssessment.reasonCode);
            reasonReference.addAll(riskAssessment.reasonReference);
            basis.addAll(riskAssessment.basis);
            prediction.addAll(riskAssessment.prediction);
            mitigation = riskAssessment.mitigation;
            note.addAll(riskAssessment.note);
            return this;
        }
    }

    /**
     * Describes the expected outcome for the subject.
     */
    public static class Prediction extends BackboneElement {
        @Binding(
            bindingName = "RiskAssessmentOutcome",
            strength = BindingStrength.ValueSet.EXAMPLE,
            description = "The condition or other outcome; e.g. death, remission, amputation, infection, etc."
        )
        private final CodeableConcept outcome;
        @Choice({ Decimal.class, Range.class })
        private final Element probability;
        @Binding(
            bindingName = "RiskAssessmentProbability",
            strength = BindingStrength.ValueSet.EXAMPLE,
            description = "The likelihood of the occurrence of a specified outcome.",
            valueSet = "http://hl7.org/fhir/ValueSet/risk-probability"
        )
        private final CodeableConcept qualitativeRisk;
        private final Decimal relativeRisk;
        @Choice({ Period.class, Range.class })
        private final Element when;
        private final String rationale;

        private volatile int hashCode;

        private Prediction(Builder builder) {
            super(builder);
            outcome = builder.outcome;
            probability = ValidationSupport.choiceElement(builder.probability, "probability", Decimal.class, Range.class);
            qualitativeRisk = builder.qualitativeRisk;
            relativeRisk = builder.relativeRisk;
            when = ValidationSupport.choiceElement(builder.when, "when", Period.class, Range.class);
            rationale = builder.rationale;
            ValidationSupport.requireValueOrChildren(this);
        }

        /**
         * One of the potential outcomes for the patient (e.g. remission, death, a particular condition).
         * 
         * @return
         *     An immutable object of type {@link CodeableConcept}.
         */
        public CodeableConcept getOutcome() {
            return outcome;
        }

        /**
         * Indicates how likely the outcome is (in the specified timeframe).
         * 
         * @return
         *     An immutable object of type {@link Element}.
         */
        public Element getProbability() {
            return probability;
        }

        /**
         * Indicates how likely the outcome is (in the specified timeframe), expressed as a qualitative value (e.g. low, medium, 
         * or high).
         * 
         * @return
         *     An immutable object of type {@link CodeableConcept}.
         */
        public CodeableConcept getQualitativeRisk() {
            return qualitativeRisk;
        }

        /**
         * Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the 
         * population in general. (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).
         * 
         * @return
         *     An immutable object of type {@link Decimal}.
         */
        public Decimal getRelativeRisk() {
            return relativeRisk;
        }

        /**
         * Indicates the period of time or age range of the subject to which the specified probability applies.
         * 
         * @return
         *     An immutable object of type {@link Element}.
         */
        public Element getWhen() {
            return when;
        }

        /**
         * Additional information explaining the basis for the prediction.
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getRationale() {
            return rationale;
        }

        @Override
        public boolean hasChildren() {
            return super.hasChildren() || 
                (outcome != null) || 
                (probability != null) || 
                (qualitativeRisk != null) || 
                (relativeRisk != null) || 
                (when != null) || 
                (rationale != null);
        }

        @Override
        public void accept(java.lang.String elementName, int elementIndex, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, elementIndex, this);
                if (visitor.visit(elementName, elementIndex, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(outcome, "outcome", visitor);
                    accept(probability, "probability", visitor);
                    accept(qualitativeRisk, "qualitativeRisk", visitor);
                    accept(relativeRisk, "relativeRisk", visitor);
                    accept(when, "when", visitor);
                    accept(rationale, "rationale", visitor);
                }
                visitor.visitEnd(elementName, elementIndex, this);
                visitor.postVisit(this);
            }
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
            Prediction other = (Prediction) obj;
            return Objects.equals(id, other.id) && 
                Objects.equals(extension, other.extension) && 
                Objects.equals(modifierExtension, other.modifierExtension) && 
                Objects.equals(outcome, other.outcome) && 
                Objects.equals(probability, other.probability) && 
                Objects.equals(qualitativeRisk, other.qualitativeRisk) && 
                Objects.equals(relativeRisk, other.relativeRisk) && 
                Objects.equals(when, other.when) && 
                Objects.equals(rationale, other.rationale);
        }

        @Override
        public int hashCode() {
            int result = hashCode;
            if (result == 0) {
                result = Objects.hash(id, 
                    extension, 
                    modifierExtension, 
                    outcome, 
                    probability, 
                    qualitativeRisk, 
                    relativeRisk, 
                    when, 
                    rationale);
                hashCode = result;
            }
            return result;
        }

        @Override
        public Builder toBuilder() {
            return new Builder().from(this);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder extends BackboneElement.Builder {
            private CodeableConcept outcome;
            private Element probability;
            private CodeableConcept qualitativeRisk;
            private Decimal relativeRisk;
            private Element when;
            private String rationale;

            private Builder() {
                super();
            }

            /**
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * 
             * <p>Adds new element(s) to the existing list
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * 
             * <p>Replaces the existing list with a new one containing elements from the Collection
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * 
             * <p>Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * 
             * <p>Adds new element(s) to the existing list
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * 
             * <p>Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * 
             * <p>Replaces the existing list with a new one containing elements from the Collection
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * One of the potential outcomes for the patient (e.g. remission, death, a particular condition).
             * 
             * @param outcome
             *     Possible outcome for the subject
             * 
             * @return
             *     A reference to this Builder instance
             */
            public Builder outcome(CodeableConcept outcome) {
                this.outcome = outcome;
                return this;
            }

            /**
             * Indicates how likely the outcome is (in the specified timeframe).
             * 
             * <p>This is a choice element with the following allowed types:
             * <ul>
             * <li>{@link Decimal}</li>
             * <li>{@link Range}</li>
             * </ul>
             * 
             * @param probability
             *     Likelihood of specified outcome
             * 
             * @return
             *     A reference to this Builder instance
             */
            public Builder probability(Element probability) {
                this.probability = probability;
                return this;
            }

            /**
             * Indicates how likely the outcome is (in the specified timeframe), expressed as a qualitative value (e.g. low, medium, 
             * or high).
             * 
             * @param qualitativeRisk
             *     Likelihood of specified outcome as a qualitative value
             * 
             * @return
             *     A reference to this Builder instance
             */
            public Builder qualitativeRisk(CodeableConcept qualitativeRisk) {
                this.qualitativeRisk = qualitativeRisk;
                return this;
            }

            /**
             * Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the 
             * population in general. (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).
             * 
             * @param relativeRisk
             *     Relative likelihood
             * 
             * @return
             *     A reference to this Builder instance
             */
            public Builder relativeRisk(Decimal relativeRisk) {
                this.relativeRisk = relativeRisk;
                return this;
            }

            /**
             * Indicates the period of time or age range of the subject to which the specified probability applies.
             * 
             * <p>This is a choice element with the following allowed types:
             * <ul>
             * <li>{@link Period}</li>
             * <li>{@link Range}</li>
             * </ul>
             * 
             * @param when
             *     Timeframe or age range
             * 
             * @return
             *     A reference to this Builder instance
             */
            public Builder when(Element when) {
                this.when = when;
                return this;
            }

            /**
             * Additional information explaining the basis for the prediction.
             * 
             * @param rationale
             *     Explanation of prediction
             * 
             * @return
             *     A reference to this Builder instance
             */
            public Builder rationale(String rationale) {
                this.rationale = rationale;
                return this;
            }

            /**
             * Build the {@link Prediction}
             * 
             * @return
             *     An immutable object of type {@link Prediction}
             */
            @Override
            public Prediction build() {
                return new Prediction(this);
            }

            protected Builder from(Prediction prediction) {
                super.from(prediction);
                outcome = prediction.outcome;
                probability = prediction.probability;
                qualitativeRisk = prediction.qualitativeRisk;
                relativeRisk = prediction.relativeRisk;
                when = prediction.when;
                rationale = prediction.rationale;
                return this;
            }
        }
    }
}
