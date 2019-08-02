package com.yq.android_recruit.pojo;

import java.io.Serializable;

public class Collection implements Serializable {
    private String personId,companyId,companyAddPerson,resumeName,recruitId;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyAddPerson() {
        return companyAddPerson;
    }

    public void setCompanyAddPerson(String companyAddPerson) {
        this.companyAddPerson = companyAddPerson;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(String recruitId) {
        this.recruitId = recruitId;
    }
}
