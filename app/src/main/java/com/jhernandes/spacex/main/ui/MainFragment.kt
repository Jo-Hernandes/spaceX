package com.jhernandes.spacex.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import com.jhernandes.spacex.R
import com.jhernandes.spacex.commons.observe
import com.jhernandes.spacex.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jhernandes.spacex.model.MissionLaunchModel
import android.content.Intent
import android.net.Uri
import com.jhernandes.spacex.commons.ConnectionException
import com.jhernandes.spacex.commons.showSnackbar
import android.view.*
import com.jhernandes.spacex.databinding.MainLaunchModalBottomsheetBinding
import com.jhernandes.spacex.databinding.MainOptionsModalBottomsheetBinding

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModel()
    private var binding: MainFragmentBinding? = null
    private var dialog: BottomSheetDialog? = null
        set(value) {
            dialog?.dismiss()
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        MainFragmentBinding.inflate(inflater).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = mainViewModel

            rvLaunches.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayout.VERTICAL
                )
            )
            rvLaunches.adapter = LaunchesAdapter(mainViewModel::handleLaunchPressed)

            toolbar.setOnMenuItemClickListener { mainViewModel.handleMenuItemPressed() }
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getSpaceXInfo()
        mainViewModel.getSpaceXLaunches()
        observeData()
    }

    private fun observeData() = with(mainViewModel) {
        observe(informationData) {
            binding?.tvCompanyInfo?.text = String.format(
                requireContext().getText(R.string.company_description).toString(),
                it.companyName,
                it.founderName,
                it.year,
                it.employees,
                it.launchSites,
                it.valuation
            )
        }

        observe(launchesData) {
            (binding?.rvLaunches?.adapter as? LaunchesAdapter)?.launches = it
        }
        observe(displayException, ::displayExceptionSnackbar)

        observe(action, ::handleEvent)
    }

    private fun handleEvent(fragmentAction: MainFragmentAction) = when (fragmentAction) {
        is MainFragmentAction.OpenLaunchDialog -> showLaunchDialog(fragmentAction.launchModel)
        is MainFragmentAction.OpenUrl -> openUrl(fragmentAction.url)
        MainFragmentAction.OpenOptionsDialog -> showOptionsDialog()
    }

    private fun showOptionsDialog() =
        openDialog(
            MainOptionsModalBottomsheetBinding.inflate(LayoutInflater.from(requireContext()))
                .apply {
                    viewModel = mainViewModel
                    rsYearRange.values = mainViewModel.yearsRange
                    rsYearRange.addOnChangeListener { slider, _, _ ->
                        mainViewModel.handleYearRange(slider.values)
                    }
                }.root
        )

    private fun showLaunchDialog(mission: MissionLaunchModel) =
        openDialog(
            MainLaunchModalBottomsheetBinding.inflate(LayoutInflater.from(requireContext()))
                .apply {
                    viewModel = mainViewModel
                    launch = mission
                }.root
        )


    private fun openDialog(view: View) {
        dialog = BottomSheetDialog(requireContext())
            .apply {
                setContentView(view)
            }
        dialog?.show()
    }

    private fun openUrl(url: String) {
        dialog?.dismiss()
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun displayExceptionSnackbar(exception: ConnectionException) {
        dialog?.dismiss()
        showSnackbar(
            text = exception.message,
            onRetry = exception.retry,
        )
    }

    override fun onDestroy() {
        binding = null
        dialog = null

        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

