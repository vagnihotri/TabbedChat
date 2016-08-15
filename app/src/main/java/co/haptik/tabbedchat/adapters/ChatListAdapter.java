package co.haptik.tabbedchat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;

import co.haptik.tabbedchat.R;
import co.haptik.tabbedchat.Utility;
import co.haptik.tabbedchat.firebase.FirebaseSingleton;
import co.haptik.tabbedchat.models.MessageListObject;
import co.haptik.tabbedchat.models.MessageObject;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private MessageListObject messageListObject;
    Context context;

    public ChatListAdapter(MessageListObject messageListObject, Context context) {
        this.messageListObject = messageListObject;
        Collections.sort(this.messageListObject.messages);
        this.context = context;
    }

    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatListAdapter.ViewHolder viewHolder, int i) {
        MessageObject messageObject = messageListObject.messages.get(i);
        viewHolder.chatName.setText(messageObject.name);
        viewHolder.chatMessage.setText(messageObject.body);
        viewHolder.chatDateTime.setText(Utility.formatDateTimeString(messageObject.messageTime));
        if(messageObject.isFav) {
            viewHolder.chatStatus.setImageResource(R.drawable.fav_on);
        } else {
            viewHolder.chatStatus.setImageResource(R.drawable.fav_off);
        }
        if(!messageObject.imageUrl.isEmpty()) {
            Picasso.with(context).load(messageObject.imageUrl).placeholder(R.drawable.placeholder).resize(60, 60).noFade().into(viewHolder.chatProfilePic);
        } else {
            viewHolder.chatProfilePic.setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return messageListObject.messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView chatName;
        private TextView chatDateTime;
        private TextView chatMessage;
        private ImageView chatStatus;
        private CircleImageView chatProfilePic;
        public ViewHolder(View view) {
            super(view);
            chatName = (TextView)view.findViewById(R.id.chat_name_text);
            chatDateTime = (TextView)view.findViewById(R.id.chat_time_text);
            chatMessage = (TextView)view.findViewById(R.id.chat_message_text);
            chatProfilePic = (CircleImageView)view.findViewById(R.id.chat_profile_pic);
            chatStatus = (ImageView)view.findViewById(R.id.chat_fav_icon);
            chatStatus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.chat_fav_icon:
                    int pos = getAdapterPosition();
                    messageListObject.messages.get(pos).isFav = !messageListObject.messages.get(pos).isFav;
                    notifyItemChanged(pos);
                    FirebaseSingleton.getInstance().setValue(messageListObject);
                    break;
            }
        }
    }
}
