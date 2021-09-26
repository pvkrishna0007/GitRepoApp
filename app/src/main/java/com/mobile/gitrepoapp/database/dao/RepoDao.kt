package com.mobile.gitrepoapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.gitrepoapp.api.response.RepoDetailModel

@Dao
interface RepoDao {

    @Query("SELECT * FROM RepoTable ORDER BY id ASC")
    suspend fun getRepoDetails(): List<RepoDetailModel>

//    @Query("SELECT * FROM RepoTable ORDER BY id ASC LIMIT :limit OFFSET :offset")
    @Query("SELECT * FROM RepoTable LIMIT :limit OFFSET :offset")
    suspend fun getRepoListByLimit(limit: Int, offset: Int = 0): List<RepoDetailModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repoDetailModel: RepoDetailModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repoDetailModelList: List<RepoDetailModel>)

    @Query("DELETE FROM RepoTable")
    suspend fun deleteAll()


    @Query("SELECT count(*) FROM RepoTable")
    suspend fun getCount(): Int

}
