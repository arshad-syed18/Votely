package com.example.voteme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteme.databinding.ItemVotesBinding;

import java.util.ArrayList;

public class viewPagerAdapter extends RecyclerView.Adapter<viewPagerAdapter.MyView> {

    ArrayList<voteQuestions> voteQuestionsArrayList;

    public viewPagerAdapter(ArrayList<voteQuestions> voteQuestionsArrayList) {
        this.voteQuestionsArrayList = voteQuestionsArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_votes, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        voteQuestions voteQuestions = voteQuestionsArrayList.get(position);

        holder.binding.questionTextView.setText(voteQuestions.getQuestionText());
        int optionSize = voteQuestions.getOptions().size();
        //set options text and visibility if more than 2
        switch(optionSize){
            case 3:
                holder.binding.optionOneBtn.setText(voteQuestions.options.get(0));
                holder.binding.optionTwoBtn.setText(voteQuestions.options.get(1));
                holder.binding.optionThreeBtn.setText(voteQuestions.options.get(2));
                holder.binding.optionThreeBtn.setVisibility(View.VISIBLE);
                break;
            case 4:
                holder.binding.optionOneBtn.setText(voteQuestions.options.get(0));
                holder.binding.optionTwoBtn.setText(voteQuestions.options.get(1));
                holder.binding.optionThreeBtn.setText(voteQuestions.options.get(2));
                holder.binding.optionFourBtn.setText(voteQuestions.options.get(3));
                holder.binding.optionThreeBtn.setVisibility(View.VISIBLE);
                holder.binding.optionFourBtn.setVisibility(View.VISIBLE);
                break;
            default:
                holder.binding.optionOneBtn.setText(voteQuestions.options.get(0));
                holder.binding.optionTwoBtn.setText(voteQuestions.options.get(1));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return voteQuestionsArrayList.size();
    }

    public static class MyView extends RecyclerView.ViewHolder{

        ItemVotesBinding binding;
        public MyView(@NonNull View itemView) {
            super(itemView);

            binding = ItemVotesBinding.bind(itemView);
        }
    }
}
