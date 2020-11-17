package entity;

import sun.util.resources.LocaleData;
import type.FamilyStatusType;
import type.SexType;

public class Contact {

    private String firstName;
    private String lastName;
    private String middleName;
    private LocaleData birthday;
    private SexType gender;
    private String citizenship;
    private FamilyStatusType familyStatus;
    private String webSite;
    private String email;
    private String currentPlaceOfWork;

    public Contact(String firstName, String lastName, String middleName, LocaleData birthday, SexType gender, String citizenship, FamilyStatusType familyStatus, String webSite, String email, String currentPlaceOfWork) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.gender = gender;
        this.citizenship = citizenship;
        this.familyStatus = familyStatus;
        this.webSite = webSite;
        this.email = email;
        this.currentPlaceOfWork = currentPlaceOfWork;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocaleData getBirthday() {
        return birthday;
    }

    public void setBirthday(LocaleData birthday) {
        this.birthday = birthday;
    }

    public SexType getGender() {
        return gender;
    }

    public void setGender(SexType gender) {
        this.gender = gender;
    }


    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public FamilyStatusType getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatusType familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentPlaceOfWork() {
        return currentPlaceOfWork;
    }

    public void setCurrentPlaceOfWork(String currentPlaceOfWork) {
        this.currentPlaceOfWork = currentPlaceOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        if (middleName != null ? !middleName.equals(contact.middleName) : contact.middleName != null) return false;
        if (birthday != null ? !birthday.equals(contact.birthday) : contact.birthday != null) return false;
        if (gender != contact.gender) return false;
        if (citizenship != null ? !citizenship.equals(contact.citizenship) : contact.citizenship != null) return false;
        if (familyStatus != contact.familyStatus) return false;
        if (webSite != null ? !webSite.equals(contact.webSite) : contact.webSite != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        return currentPlaceOfWork != null ? currentPlaceOfWork.equals(contact.currentPlaceOfWork) : contact.currentPlaceOfWork == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (citizenship != null ? citizenship.hashCode() : 0);
        result = 31 * result + (familyStatus != null ? familyStatus.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (currentPlaceOfWork != null ? currentPlaceOfWork.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", citizenship='" + citizenship + '\'' +
                ", familyStatus=" + familyStatus +
                ", webSite='" + webSite + '\'' +
                ", email='" + email + '\'' +
                ", currentPlaceOfWork='" + currentPlaceOfWork + '\'' +
                '}';
    }
}
