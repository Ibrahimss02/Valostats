package com.drunken.valostats.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.drunken.valostats.R
import com.drunken.valostats.databinding.*
import com.drunken.valostats.models.Agent
import com.drunken.valostats.models.Map
import com.drunken.valostats.models.Spray
import com.drunken.valostats.utils.AgentDiffUtil
import com.drunken.valostats.utils.BundleDiffUtil
import com.drunken.valostats.utils.MapDiffUtil
import com.drunken.valostats.utils.SprayDiffUtil
import com.drunken.valostats.viewModels.HomepageViewModel
import com.drunken.valostats.views.adapters.HomepageAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Homepage : Fragment() {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var bottomSheet: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomepageBinding.inflate(layoutInflater)

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheetHome).apply {
            expandedOffset = 125
            peekHeight = 500
            isFitToContents = false

            addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        restoreSheet()

                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // Nothing
                }
            })
        }

        val viewModel = ViewModelProvider(this)[HomepageViewModel::class.java]
        binding.lifecycleOwner = this

        binding.agentsCardView.setOnClickListener {
            binding.rvHomepage.adapter = null
            viewModel.fetchAllAgents()
            binding.fetchingIndicator.visibility = View.VISIBLE
            fetchingItem("Agents")
            viewModel.allAgent.observe(viewLifecycleOwner) {
                val adapter = HomepageAdapter<Agent, AgentItemLayoutBinding>(AgentDiffUtil(), R.layout.agent_item_layout)
                binding.rvHomepage.adapter = adapter
                adapter.submitList(it)
                binding.fetchingIndicator.visibility = View.INVISIBLE
            }
        }

        binding.mapsCardView.setOnClickListener {
            binding.rvHomepage.adapter = null
            viewModel.fetchAllMaps()
            binding.fetchingIndicator.visibility = View.VISIBLE
            fetchingItem("Maps")
            viewModel.allMaps.observe(viewLifecycleOwner) {
                val adapter = HomepageAdapter<Map, MapItemLayoutBinding>(MapDiffUtil(), R.layout.map_item_layout)
                binding.rvHomepage.adapter = adapter
                adapter.submitList(it)
                binding.fetchingIndicator.visibility = View.INVISIBLE
            }
        }
        
        binding.bundlesCardView.setOnClickListener {
            binding.rvHomepage.adapter = null
            viewModel.fetchAllBundles()
            binding.fetchingIndicator.visibility = View.VISIBLE
            fetchingItem("Bundles")
            viewModel.allBundles.observe(viewLifecycleOwner) {
                val adapter = HomepageAdapter<com.drunken.valostats.models.Bundle, BundleItemLayoutBinding>(BundleDiffUtil(), R.layout.bundle_item_layout)
                binding.rvHomepage.adapter = adapter
                adapter.submitList(it)
                binding.fetchingIndicator.visibility = View.INVISIBLE
            }
        }

        binding.spraysCardView.setOnClickListener {
            binding.rvHomepage.adapter = null
            viewModel.fetchAllSprays()
            binding.fetchingIndicator.visibility = View.VISIBLE
            fetchingItem("Sprays")
            viewModel.allSpray.observe(viewLifecycleOwner) {
                val adapter = HomepageAdapter<Spray, SprayItemLayoutBinding>(SprayDiffUtil(), R.layout.spray_item_layout)
                binding.rvHomepage.adapter = adapter
                adapter.submitList(it)
                binding.fetchingIndicator.visibility = View.INVISIBLE
            }
        }

        binding.comingSoonCardView.setOnClickListener {
            Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun restoreSheet() {
        binding.spraysCardView.visibility = View.VISIBLE
        binding.bundlesCardView.visibility = View.VISIBLE
        binding.comingSoonCardView.visibility = View.VISIBLE
        binding.rvHomepage.visibility = View.INVISIBLE
        binding.rvTitle.visibility = View.INVISIBLE
        binding.fetchingIndicator.visibility = View.INVISIBLE
        binding.rvHomepage.adapter = null
    }

    private fun fetchingItem(fetchingType: String) {
        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
        binding.spraysCardView.visibility = View.INVISIBLE
        binding.bundlesCardView.visibility = View.INVISIBLE
        binding.comingSoonCardView.visibility = View.INVISIBLE
        binding.rvHomepage.visibility = View.VISIBLE
        binding.rvTitle.visibility = View.VISIBLE
        binding.rvTitle.text = fetchingType
    }
}