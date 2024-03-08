package di

import data.repository.JobsRepositoryImpl
import domain.repository.JobsRepository
import jobs.JobsService


object JobsModule {
    private val jobsService: JobsService by lazy {
        JobsService()
    }

    val jobsRepository: JobsRepository by lazy {
        JobsRepositoryImpl(jobsService = jobsService)
    }
}