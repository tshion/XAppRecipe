package work.shion.baser

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@Config(sdk = [28])
@RunWith(AndroidJUnit4::class)
class RxSampleTest {

    companion object {

        @BeforeClass
        @JvmStatic
        fun setupClass() {
            val immediate = object : Scheduler() {
                override fun createWorker() = ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)

                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit) = super.scheduleDirect(run, 0, unit)
            }

            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { _ -> immediate }
            RxJavaPlugins.setInitIoSchedulerHandler { _ -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { _ -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { _ -> immediate }
        }
    }


    @Test
    fun testCompletable() {
        Completable.create { emitter ->
            emitter.onComplete()
            emitter.onComplete()
        }.subscribe({
            println("complete!")
        }, { error ->
            println(error)
        })
    }

    /**
     * 上手くいく
     */
    @Test
    fun testTimer() {
        println("before")

        val count = 3L
        val period = 1000L
        val unit = TimeUnit.MILLISECONDS

        val scheduler = TestScheduler()
        val subscriber = Flowable
                .interval(period, unit, scheduler)
                .take(count)
                .map {
                    println("timer: ${1000 * (it + 1)}[s]")
                    it
                }
                .test()
        scheduler.advanceTimeBy(count, TimeUnit.SECONDS)
        subscriber.await().assertValues(0, 1, 2)

        println("after")
    }
}
