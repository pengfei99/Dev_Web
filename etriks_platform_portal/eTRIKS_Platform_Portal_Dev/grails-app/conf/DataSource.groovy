
//The config to connect postgresql db server

dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.postgresql.Driver"
   /* username = "postgres"
    password = "postgres"*/

    //readOnly = false
}

hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=false
   // cache.provider_class='org.hibernate.cache.EhCacheProvider'
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
   // cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' //hibernate 4
}
// environment specific settings
environments {
    development {
        dataSource {
         /*memory db*/
            driverClassName="org.h2.Driver"
            dbCreate="create-drop"
            url="jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            username="sa"
            password=""
            /*Real db on a vm in etriks cloud, need to config pg_hba for new comer*/
            /*dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:postgresql://134.158.242.19:5432/portal"
            username = "postgres"
            password = "postgres"*/
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://portal-test.etriks.org:5432/portalDB"
            username = "postgres"
            password = "Hubert@1983"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            /*url = "jdbc:postgresql://portal-test.etriks.org:5432/portalDB"*/
            url = "jdbc:postgresql://portal.etriks.org:5432/portalDB"
            username = "postgres"
            password = "Hubert@1983"
        }
    }
}


