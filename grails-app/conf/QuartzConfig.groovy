environments {
    development {
        quartz {
            autoStartup = true
            jdbcStore = false
            waitForJobsToCompleteOnShutdown = true
            exposeSchedulerInRepository = false

            props {
                scheduler.skipUpdateCheck = true
            }
        }
    }
    test {
        quartz {
            autoStartup = true
            jdbcStore = true
            waitForJobsToCompleteOnShutdown = true
            exposeSchedulerInRepository = false
            
            props {
                //============================================================================
                // Configure Main Scheduler Properties
                //============================================================================
                scheduler.skipUpdateCheck = true
                scheduler.instanceName = 'ClusteredScheduler'
                scheduler.instanceId = 'AUTO'
                
                //============================================================================
                // Configure ThreadPool
                //============================================================================
                threadPool.'class' = 'org.quartz.simpl.SimpleThreadPool'
                threadPool.threadCount = 25
                threadPool.threadPriority = 5
                
                //============================================================================
                // Configure JobStore
                //============================================================================
                jobStore.misfireThreshold = 60000
                
                jobStore.'class' = 'org.quartz.impl.jdbcjobstore.JobStoreTX'
                jobStore.driverDelegateClass = 'org.quartz.impl.jdbcjobstore.PostgreSQLDelegate'
                jobStore.useProperties = false
                jobStore.tablePrefix = 'QRTZ_'
                jobStore.isClustered = true
                jobStore.clusterCheckinInterval = 2000
                
                plugin.shutdownhook.'class' = 'org.quartz.plugins.management.ShutdownHookPlugin'
                plugin.shutdownhook.cleanShutdown = true
            }
        }
    }
    production {
        quartz {
            autoStartup = true
            jdbcStore = false
            waitForJobsToCompleteOnShutdown = true
            exposeSchedulerInRepository = false

            props {
                scheduler.skipUpdateCheck = true
            }
        }
    }
}
