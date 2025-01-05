package com.harsha.buildlinkbetweenadeptandcompanies.Models

class User {
    var image: String? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null

    constructor()
    constructor(image: String?, name: String?, email: String?, password: String?) {
        this.image = image
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(name: String?, email: String?, password: String?) {
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }
}

class Details {
    var occupation: String? = null
    var firstname: String? = null
    var lastname: String? = null
    var bio: String? = null
    var phone: String? = null
    var dob: String? = null
    var highestdegree:String?=null
    var years:String?=null
    var specialization:String?=null
    var languages:String?=null

    constructor()
    constructor(
        occupation: String?,
        firstname: String?,
        lastname: String?,
        bio: String?,
        phone: String?,
        dob: String?,
        highestdegree:String?,
        years:String?,
        specialization:String?,
        languages:String?
    ) {
        this.occupation = occupation
        this.firstname = firstname
        this.lastname = lastname
        this.bio = bio
        this.phone = phone
        this.dob = dob
        this.highestdegree=highestdegree
        this.years=years
        this.specialization=specialization
        this.languages=languages
    }
}
class Jobs
{
    var jobtitle:String?=null
    var experience:String?=null
    var skills:String?=null
    var jobstatus:String?=null
    var salary:String?=null
    var aboutjob:String?=null
    var profileLink:String?=null
    var nameLink:String?=null
    var occupationLink:String?=null
    constructor()
    constructor(
        jobtitle: String?,
        experience: String?,
        skills: String?,
        jobstatus: String?,
        salary: String?,
        aboutjob: String
    ) {
        this.jobtitle = jobtitle
        this.experience = experience
        this.skills = skills
        this.jobstatus = jobstatus
        this.salary = salary
        this.aboutjob = aboutjob
    }

    constructor(
        jobtitle: String?,
        experience: String?,
        skills: String?,
        jobstatus: String?,
        salary: String?,
        aboutjob: String?,
        profileLink: String?,
        nameLink:String?,
        occupationLink:String?
    ) {
        this.jobtitle = jobtitle
        this.experience = experience
        this.skills = skills
        this.jobstatus = jobstatus
        this.salary = salary
        this.aboutjob = aboutjob
        this.profileLink = profileLink
        this.nameLink=nameLink
        this.occupationLink=occupationLink
    }

}