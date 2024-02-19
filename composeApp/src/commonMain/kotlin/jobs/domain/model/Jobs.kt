package jobs.domain.model

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
data class Jobs(
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
    val companyLogo: DrawableResource,
    val companyMotto : String,
    val aboutUs: String,
    val industry: String,
    val city: String,
    val country: String,
    val address: String,
    val salary: String,
    val postedDate: String,
    val link: String
)
