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
            jdbcStore = false
            waitForJobsToCompleteOnShutdown = true
            exposeSchedulerInRepository = false

            props {
                scheduler.skipUpdateCheck = true
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
