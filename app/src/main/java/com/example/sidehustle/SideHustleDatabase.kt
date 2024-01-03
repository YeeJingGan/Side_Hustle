package com.example.sidehustle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [
        EntityApplication::class,
        EntityEmployee::class,
        EntityEmployer::class,
        EntityFavorite::class,
        EntityJob::class,
        EntityLanguage::class,
        EntityNegotiation::class,
        EntityRating::class,
        EntityRequirement::class,
        EntityResume::class
    ],
    exportSchema = false,
    version = 1
)
abstract class SideHustleDatabase : RoomDatabase() {
    abstract fun applicationDao(): EntityApplicationDao
    abstract fun employeeDao(): EntityEmployeeDao
    abstract fun employerDao(): EntityEmployerDao
    abstract fun favoriteDao(): EntityFavoriteDao
    abstract fun jobDao(): EntityJobDao
    abstract fun languageDao(): EntityLanguageDao
    abstract fun negotiationDao(): EntityNegotiationDao
    abstract fun ratingDao(): EntityRatingDao
    abstract fun requirementDao(): EntityRequirementDao
    abstract fun resumeDao(): EntityResumeDao

    companion object {
        @Volatile
        private var INSTANCE: SideHustleDatabase? = null

        fun getDatabase(context: Context): SideHustleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SideHustleDatabase::class.java,
                    "side_hustle_schema"
                ).addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {

                                val employeeDao = getDatabase(context).employeeDao()
                                val employerDao = getDatabase(context).employerDao()
                                val jobDao = getDatabase(context).jobDao()

                                val employees = listOf(
                                    EntityEmployee(0, "Gan Yee Jing", "yeejing@mail.com", "Employee1!"),
                                    EntityEmployee(0, "Yeap Jie Shen", "jieshen@mail.com", "Employee2!"),
                                    EntityEmployee(0, "Jerome Subash", "jerome@mail.com", "Employee3!"),
                                )

                                val employers = listOf(
                                    EntityEmployer(0,"Poptics Sdn. Bhd.","poptics@mail.com","Employer1!"),
                                    EntityEmployer(0,"Lotus Sdn. Bhd.","lotus@mail.com","Employer2!"),
                                    EntityEmployer(0,"Giant Sdn. Bhd.","giant@mail.com","Employer3!"),
                                )
                                val jobs = listOf(
                                    EntityJob(0,1,"Job 1 Employer 1","Kuala Lumpur","Address 1 Employer 1","10001",70,"04/01/2024","05/01/2024","14:05","18:05","Desciption 1 Employee 1","APPROVED"),
                                    EntityJob(0,1,"Job 2 Employer 1","Selangor","Address 2 Employer 1","10002",80,"06/01/2024","07/01/2024","16:05","20:05","Desciption 2 Employee 1","APPROVED"),
                                    EntityJob(0,2,"Job 1 Employer 2","Pahang","Address 1 Employer 2","20001",90,"04/01/2024","05/01/2024","08:05","18:05","Desciption 1 Employee 2","APPROVED"),
                                    EntityJob(0,2,"Job 2 Employer 2","Melaka","Address 2 Employer 2","20002",100,"04/01/2024","06/01/2024","10:05","14:05","Desciption 2 Employee 2","APPROVED"),
                                    EntityJob(0,3,"Job 1 Employer 3","Perak","Address 1 Employer 3","30001",110,"05/01/2024","08/01/2024","09:05","12:05","Desciption 1 Employee 3","APPROVED"),
                                    EntityJob(0,3,"Job 2 Employer 3","Perlis","Address 2 Employer 3","30002",120,"07/01/2024","09/01/2024","13:05","18:05","Desciption 2 Employee 3","APPROVED"),
                                )



                                for (i in 0..<employees.size) {
                                    employeeDao.insert(employees[i])
                                }
                                for (i in 0..<employers.size) {
                                    employerDao.insert(employers[i])
                                }

                                for (i in 0..<jobs.size) {
                                    jobDao.insert(jobs[i])
                                }
                            }
                        }
                    })

                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}