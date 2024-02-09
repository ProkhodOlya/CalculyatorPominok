package com.calculyatorpominok.presentation.details

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.calculyatorpominok.R
import com.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private var textViewDateOfDeathCaption: TextView? = null
    private var textViewDateOfDeathDescription: TextView? = null
    private var toolbar: Toolbar? = null
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        toolbar = view.findViewById<Toolbar?>(R.id.toolbar).apply {
            val backIcon = AppCompatResources.getDrawable(
                context,
                androidx.appcompat.R.drawable.abc_ic_ab_back_material
            )
            val typedValue = TypedValue();
            requireContext().theme.resolveAttribute(
                androidx.appcompat.R.attr.colorPrimary,
                typedValue,
                true
            );
            val color = ContextCompat.getColor(context, typedValue.resourceId)
            backIcon?.setTint(color)
            navigationIcon = backIcon
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

        textViewDateOfDeathCaption = view.findViewById(R.id.textDateOfDeathCaption)
        textViewDateOfDeathDescription = view.findViewById(R.id.textViewDateOfDeath)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToState()
        val dayOfCommemoration = arguments?.getInt(ARGS_DAY_OF_COMMEMORATION, -1) ?: -1
        viewModel.start(dayOfCommemoration = dayOfCommemoration)
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { detailsState ->
                    textViewDateOfDeathCaption?.text = getString(detailsState.dayDateOfDeathCaption)
                    textViewDateOfDeathDescription?.text = HtmlCompat.fromHtml(
                        getString(detailsState.detailsDateOfDeathDescription),
                        FROM_HTML_MODE_LEGACY
                    )
                }
            }
        }
    }

    companion object {
        const val DETAILS_FRAGMENT = "detailsFragment"

        fun newInstance(dayOfCommemoration: Int) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARGS_DAY_OF_COMMEMORATION, dayOfCommemoration)
                }
            }
    }
}