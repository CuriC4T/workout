package com.curic4t.android.workout.view.main.ui.main.location

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.curic4t.android.workout.R
import com.curic4t.android.workout.databinding.FragmentLocationSettingBinding
import com.curic4t.android.workout.domain.model.location.LocationModel
import com.curic4t.android.workout.view.main.ui.address.SearchAddressActivity
import com.curic4t.android.workout.view.main.ui.main.MainAndroidViewModel
import com.curic4t.android.workout.view.main.ui.utils.LogUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationSettingFragment : Fragment() {
    private lateinit var _binding: FragmentLocationSettingBinding

    //fragment간 viewmodel 공유
    private val viewModel: MainAndroidViewModel by activityViewModels()

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                intent?.let {
                    viewModel._testLiveData.postValue(
                        LocationModel(
                            0L, 0L, "a", "a", "a"
                        )
                    )
                    LogUtil.d(
                        "LocationSettingFragment", "${it.getStringExtra("postCode")} " +
                                "${it.getStringExtra("fullRoadAddr")} " +
                                "${it.getStringExtra("jibunAddr")}"
                    )
                    //Navigation argument 전달은 되도록 지양 -> viewmodel로 전라
//                    val bundle = bundleOf(
//                        "postCode" to it.getStringExtra("postCode"),
//                        "fullRoadAddr" to it.getStringExtra("fullRoadAddr"),
//                        "jibunAddr" to it.getStringExtra("jibunAddr")
//                    )
//                    this@LocationSettingFragment.findNavController().navigate(
//                        R.id.DashBoardFragment, bundle
//                    )
                    findNavController().navigate(R.id.DashBoardFragment)

                }

            }
        }
    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationSettingBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLocationSettingSearch.setOnClickListener {

            //startActivity()
            activityResultLauncher.launch(Intent(context, SearchAddressActivity::class.java))


        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LocationSettingFragment().apply {

            }
    }
}