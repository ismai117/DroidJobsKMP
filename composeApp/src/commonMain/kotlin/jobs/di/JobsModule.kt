package jobs.di

import data.repository.JobsRepositoryImpl
import jobs.data.service.JobsService
import jobs.domain.repository.JobsRepository
import org.koin.dsl.module
import jobs.presentation.JobsViewModel


val jobsModule = module {
    single { JobsService() }
    single<JobsRepository> {  JobsRepositoryImpl(get()) }
    factory { JobsViewModel(get()) }
}