package com.example.searchsuggestions.presentation.search_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchsuggestions.R

class SearchSuggestionsAdapter(
    private val onFillClick: (suggestion: String) -> Unit,
    private val onSearchClick: () -> Unit
) :
    RecyclerView.Adapter<SearchSuggestionsAdapter.ViewHolder>() {

    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(
        view: View,
        val onFillClick: (suggestion: String) -> Unit,
        val onSearchClick: () -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.suggestion_text_view)
        private val suggestionLayout: LinearLayout = view.findViewById(R.id.suggestion_layout)
        private val fillButton: ImageView = view.findViewById(R.id.fill_button)

        init {

        }

        fun bind(suggestion: String) {
            textView.text = suggestion
            suggestionLayout.setOnClickListener {
                onSearchClick()
            }
            fillButton.setOnClickListener {
                onFillClick(suggestion)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_suggestion_item, parent, false)

        return ViewHolder(view, onFillClick, onSearchClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        /*
        SpannableString str = new SpannableString("Highlighted. Not highlighted.")
        str.setSpan(new BackgroundColorSpan (Color.YELLOW), 0, 11, 0)

         */
    }
}