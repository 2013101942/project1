package com.yq.android_recruit.service;

import com.yq.android_recruit.pojo.DetailedResume;
import com.yq.android_recruit.pojo.RecruitInfo;

import java.util.List;

public interface IDetailedService {
    DetailedResume loadDetailedResume(String userId, String resumeName);
    RecruitInfo loadDetailedRecruit(String company, String recruitId);
    List<String> getMyJobs(String companyId);
}
