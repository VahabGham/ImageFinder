package com.vahab.imagefinder.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.vahab.imagefinder.data.asEntity
import com.vahab.imagefinder.data.database.AppDatabase
import com.vahab.imagefinder.data.database.ImageDao
import com.vahab.imagefinder.data.imageData
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AppDatabaseTest {

    private lateinit var imageDao: ImageDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        imageDao = appDatabase.imageDao()
    }

    @Test
    fun testCache() = runTest {
        imageDao.insertUpdateImages(
            images = imageData.map {
                it.asEntity()
            }
        )

        imageDao.getAll().test {
            val list = awaitItem()

            Assert.assertEquals(
                list.size,
                imageData.size
            )

            Assert.assertEquals(
                list.first().userName,
                imageData.first().user
            )

            Assert.assertEquals(
                list.last().userName,
                imageData.last().user
            )
        }
    }

    @Test
    fun testCacheUpdate() = runTest {
        val droppedItems = 1
        imageDao.insertUpdateImages(
            images = imageData
                .toMutableList()
                .drop(droppedItems)
                .map {
                    it.asEntity()
                }
        )

        imageDao.getAll().test {
            val list = awaitItem()

            Assert.assertTrue(
                list.size == imageData.size - droppedItems
            )

            Assert.assertTrue(
                list.first().userName != imageData.first().user
            )

            Assert.assertTrue(
                list.last().userName == imageData.last().user
            )
        }
    }
}
