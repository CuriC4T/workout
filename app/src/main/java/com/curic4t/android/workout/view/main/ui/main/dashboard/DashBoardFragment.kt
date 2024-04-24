package com.curic4t.android.workout.view.main.ui.main.dashboard

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.curic4t.android.workout.databinding.FragmentMainBinding
import com.curic4t.android.workout.view.main.ui.base.BaseFragment
import com.curic4t.android.workout.view.main.ui.main.MainAndroidViewModel
import com.curic4t.android.workout.view.main.ui.utils.LocationUtil
import com.curic4t.android.workout.view.main.ui.utils.LogUtil
import com.curic4t.android.workout.view.main.ui.utils.PermissionUtil
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class DashBoardFragment : BaseFragment() {
    protected var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!

    @Inject
    lateinit var permissionUtil: PermissionUtil

    @Inject
    lateinit var locationUtil: LocationUtil

    lateinit var mapView: MapView

    companion object {
        fun newInstance() = DashBoardFragment()
    }

    private  val viewModel: MainAndroidViewModel by activityViewModels()
    //fragment간 viewmodel 공유

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this)[MainAndroidViewModel::class.java]
        viewModel.locationLiveData.updateLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeSetting()
    }

    fun onAttached(context: Context) {
        super.onAttach(context)

    }

    private fun initView() {
        mapView = binding.mapviewMain
        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // 지도 API 가 정상적으로 종료될 때 호출됨

            }

            override fun onMapError(p0: Exception?) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }

        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(p0: KakaoMap) {
                // 인증 후 API 가 정상적으로 실행될 때 호출됨
            }

            override fun getZoomLevel(): Int {
                return super.getZoomLevel()
            }

            override fun getViewName(): String {
                return super.getViewName()
            }

            override fun getTag(): Any {
                return super.getTag()
            }

            override fun getPosition(): LatLng {
                return super.getPosition()
            }

            override fun isVisible(): Boolean {
                return super.isVisible()
            }
        })
        binding.testButton.setOnClickListener {
            val ceh = CoroutineExceptionHandler { coroutineContext, throwable ->
                LogUtil.e("COROUTINE", throwable.message.toString())
            }
            CoroutineScope(Dispatchers.IO).launch(ceh) {
                val hasPermission = permissionUtil.checkEssentialPermission(
                    context = requireContext(),
                    PermissionUtil.locationPermissions
                )
                if (hasPermission) {
                    viewModel.getTodayData()
                } else {
                    //todo 권한이 없음
                }
            }
        }

        binding.locationButton.setOnClickListener {
            LogUtil.d(
                "GridLocation",
                "${viewModel.locationLiveData.value?.first} ${viewModel.locationLiveData.value?.second}"
            )
            LogUtil.d("test","${viewModel._testLiveData.value?.postCode}")

        }


    }

    private fun observeSetting() {
        viewModel.todayLiveData.observe(viewLifecycleOwner) {
            it.forEach {
                binding.message.text = it.test
            }
        }

        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            LogUtil.d("GridLocation", "update")

            val gridPair = viewModel.latlngToGrid(it)
            LogUtil.d("GridLocation", "${gridPair.first} ${gridPair.second}")
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}