package di

import data.repository.JobsRepositoryImpl
import domain.repository.JobsRepository
import jobs.JobsService
import org.koin.dsl.module
import presentation.JobsViewModel


val jobsModule = module {
    single<JobsService> {  JobsService() }
    single<JobsRepository> {  JobsRepositoryImpl(get()) }
    factory { JobsViewModel(get()) }
}