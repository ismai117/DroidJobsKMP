package di

import data.repository.JobsRepositoryImpl
import data.service.JobsService
import domain.repository.JobsRepository


object JobsModule {
    private val jobsService: JobsService by lazy {
        JobsService()
    }

    val jobsRepository: JobsRepository by lazy {
        JobsRepositoryImpl(jobsService = jobsService)
    }
}