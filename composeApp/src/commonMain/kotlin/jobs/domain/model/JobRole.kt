package jobs.domain.model

import kotlin.math.exp


data class JobRole(
    val id: String,
    val title: String,
    val role: String,
    val description: String,
    val requirements: String,
    val skills: List<String>,
    val employmentType: String,
    val workEnvironment: String,
    val companyBenefits: String,
    val visaSponsorship: String,
    val experienceLevel: String,
    val company: String,
    val companyLogo: String,
    val companyMotto : String,
    val aboutUs: String,
    val industry: String,
    val city: String,
    val country: String,
    val address: String,
    val salary: String,
    val postedDate: String,
    val link: String
){
    fun doesMatchSearchQuery(value: String): Boolean{
        val combo = listOf(
            experienceLevel,
            role,
            title
        )
        return combo.any {
            it.contains(value, ignoreCase = true)
        }
    }
}