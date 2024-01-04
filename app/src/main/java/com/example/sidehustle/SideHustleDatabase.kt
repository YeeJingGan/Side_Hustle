package com.example.sidehustle

import android.content.Context
import android.util.Log
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
                                val requirementDao = getDatabase(context).requirementDao()
                                val applicationDao = getDatabase(context).applicationDao()
                                val languageDao = getDatabase(context).languageDao()
                                val negotiationDao = getDatabase(context).negotiationDao()
                                val ratingDao = getDatabase(context).ratingDao()

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
                                    EntityJob(0,1,"Job 1 Employer 1","Terengganu","Address 1 Employer 1","10001",40,"12/12/2023","13/12/2024","14:05","18:05","Desciption 1 Employee 1","HISTORY"),
                                    EntityJob(0,1,"Job 2 Employer 1","Kuala Lumpur","Address 2 Employer 1","10002",50,"03/01/2024","04/01/2024","14:05","18:05","Desciption 2 Employee 1","APPROVED"),
                                    EntityJob(0,1,"Job 3 Employer 1","Johor","Address 3 Employer 1","10003",60,"04/01/2024","05/01/2024","14:05","18:05","Desciption 3 Employee 1","APPROVED"),
                                    EntityJob(0,1,"Job 4 Employer 1","Negeri Sembilan","Address 4 Employer 1","10004",70,"06/01/2024","08/01/2024","14:05","18:05","Desciption 4 Employee 1","APPROVED"),
                                    EntityJob(0,1,"Job 5 Employer 1","Selangor","Address 5 Employer 1","10005",70,"08/01/2024","09/01/2024","14:05","18:05","Desciption 5 Employee 1","PENDING"),
                                    EntityJob(0,2,"Job 1 Employer 2","Selangor","Address 1 Employer 2","20001",80,"06/01/2024","07/01/2024","16:05","20:05","Desciption 1 Employee 2","APPROVED"),
                                    EntityJob(0,2,"Job 2 Employer 2","Pahang","Address 2 Employer 2","20002",90,"04/01/2024","05/01/2024","08:05","18:05","Desciption 2 Employee 2","APPROVED"),
                                    EntityJob(0,2,"Job 3 Employer 2","Melaka","Address 3 Employer 2","20003",100,"04/01/2024","06/01/2024","10:05","14:05","Desciption 3 Employee 2","APPROVED"),
                                    EntityJob(0,3,"Job 1 Employer 3","Perak","Address 1 Employer 3","30001",110,"05/01/2024","08/01/2024","09:05","12:05","Desciption 1 Employee 3","APPROVED"),
                                    EntityJob(0,3,"Job 2 Employer 3","Perlis","Address 2 Employer 3","30002",120,"07/01/2024","09/01/2024","13:05","18:05","Desciption 2 Employee 3","APPROVED"),
                                )

                                val requirements = listOf(
                                    EntityRequirement(0,1,"Requirement 1 Job 1"),
                                    EntityRequirement(0,2,"Requirement 1 Job 2"),
                                    EntityRequirement(0,2,"Requirement 2 Job 2"),
                                    EntityRequirement(0,3,"Requirement 1 Job 3"),
                                    EntityRequirement(0,3,"Requirement 2 Job 3"),
                                    EntityRequirement(0,4,"Requirement 1 Job 4"),
                                    EntityRequirement(0,4,"Requirement 2 Job 4"),
                                )

                                val applications = listOf(
                                    EntityApplication(1,1,"ACCEPTED"),
                                    EntityApplication(2,2,"ACCEPTED"),
                                    EntityApplication(1,3,"ACCEPTED"),
                                    EntityApplication(2,3,"ACCEPTED"),
                                    EntityApplication(1,4,"NEGOTIATING"),
                                )

                                val languages = listOf(
                                    EntityLanguage(0,1,1,"English","Native"),
                                    EntityLanguage(0,2,2,"English","Good"),
                                    EntityLanguage(0,1,3,"English","Native"),
                                    EntityLanguage(0,2,3,"English","Native"),
                                    EntityLanguage(0,1,4,"English","Native"),
                                    EntityLanguage(0,1,4,"Malay","Moderate"),
                                )

                                val negotiations = listOf(
                                    EntityNegotiation(0,1,1,50,"Comment 1 Application 1 Job 1 Employer 1","EMPLOYEE"),
                                    EntityNegotiation(0,2,2,50,"Comment 1 Application 1 Job 2 Employer 1","EMPLOYEE"),
                                    EntityNegotiation(0,1,3,70,"Comment 1 Application 1 Job 3 Employer 1","EMPLOYEE"),
                                    EntityNegotiation(0,2,3,80,"Comment 2 Application 1 Job 3 Employer 1","EMPLOYEE"),
                                    EntityNegotiation(0,1,4,90,"Comment 1 Application 1 Job 4 Employer 1","EMPLOYEE"),
                                    EntityNegotiation(0,1,4,80,"Comment 2 Application 1 Job 4 Employer 1","EMPLOYER"),
                                    EntityNegotiation(0,1,4,90,"Comment 3 Application 1 Job 4 Employer 1","EMPLOYEE"),
                                )

                                val ratings = listOf(
                                    EntityRating(0,1,1,4,"Employee Comment 1","EMPLOYEE"),
                                    EntityRating(0,1,1,4,"Employer Comment 1","EMPLOYER"),
                                )

                                for (i in 0..<employees.size) {
                                    employeeDao.insert(employees[i])
                                }
                                Log.i("employees","employees created")

                                for (i in 0..<employers.size) {
                                    employerDao.insert(employers[i])
                                }
                                Log.i("employers","employers created")

                                for (i in 0..<jobs.size) {
                                    jobDao.insert(jobs[i])
                                }
                                Log.i("jobs","jobs created")
                                for (i in 0..<requirements.size) {
                                    requirementDao.insert(requirements[i])
                                }
                                Log.i("requirements","requirements created")

                                for (i in 0..<applications.size) {
                                    applicationDao.insert(applications[i])
                                }
                                Log.i("applications","applications created")

                                for (i in 0..<languages.size) {
                                    languageDao.insert(languages[i])
                                }
                                Log.i("languages","languages created")

                                for (i in 0..<negotiations.size) {
                                    negotiationDao.insert(negotiations[i])
                                }
                                Log.i("negotiations","negotiations created")

                                for (i in 0..<ratings.size) {
                                    ratingDao.insert(ratings[i])
                                }
                                Log.i("ratings","ratings created")

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