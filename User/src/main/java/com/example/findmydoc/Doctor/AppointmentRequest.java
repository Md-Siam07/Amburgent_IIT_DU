package com.example.findmydoc.Doctor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmydoc.Patient_Model;
import com.example.findmydoc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentRequest extends Fragment {



    FloatingActionButton dragView;
    ArrayList<Patient_Model> memberInfos=new ArrayList<>();
    float dX;
    float dY;
    int lastAction;
    FrameLayout frameLayout;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    RecycleAdapter recycleAdapter;
    AlertDialog alertDialog;
    String teacher_id,name,email,image_path,device_id,phone_number;
    TextView tv_name,tv_email,tv_phone_number,no_item,no_class_teacher;
    Button btn_rating,btn_profile;
    LinearLayout view_profile;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser;
    String user_id="";
    public AppointmentRequest() {
        // Required empty public constructor
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.patient_list, container, false);
        recyclerView=view.findViewById(R.id.recycle);
        view_profile=view.findViewById(R.id.view_profile);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        no_item=view.findViewById(R.id.no_item);
        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null) user_id=firebaseUser.getUid();
        recycleAdapter=new RecycleAdapter(memberInfos);
        recyclerView.setAdapter(recycleAdapter);
        getAllPatient();
        return view;
    }
    public void getAllPatient(){
        progressDialog.show();
        Query query= db.collection("users");//.whereEqualTo("appoint_doctor_id",user_id);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                progressDialog.dismiss();
                QuerySnapshot queryDocumentSnapshot=task.getResult();
                if(queryDocumentSnapshot.size()>0){
                    for(QueryDocumentSnapshot queryDocumentSnapshot1:queryDocumentSnapshot){
                        String user_id="";
                        String user_name="";
                        String phone_number="";
                        String age="";
                        String image_path="";
                        String device_id="";
                        if(queryDocumentSnapshot1.contains("user_id")) user_id=queryDocumentSnapshot1.get("user_id").toString();
                        if(queryDocumentSnapshot1.contains("user_name")) user_name=queryDocumentSnapshot1.get("user_name").toString();
                        if(queryDocumentSnapshot1.contains("phone_no")) phone_number=queryDocumentSnapshot1.get("phone_no").toString();
                        if(queryDocumentSnapshot1.contains("age")) age=queryDocumentSnapshot1.get("age").toString();
                        if(queryDocumentSnapshot1.contains("image_path")) image_path=queryDocumentSnapshot1.get("image_path").toString();
                        if(queryDocumentSnapshot1.contains("device_id")) device_id=queryDocumentSnapshot1.get("device_id").toString();
                        memberInfos.add(new Patient_Model(user_id,user_name,phone_number,age,image_path,device_id));
                    }
                    recycleAdapter.notifyDataSetChanged();
                }
                else{
                    no_item.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }


            }
        });
    }
    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewAdapter>{

        ArrayList<Patient_Model> memberInfos;
        public RecycleAdapter(ArrayList<Patient_Model> memberInfos){
            this.memberInfos=memberInfos;
        }
        public  class ViewAdapter extends RecyclerView.ViewHolder{

            View mView;
            Button accept,cancel;
            LinearLayout linearLayout;
            CircleImageView imageView;
            TextView name,age,phone_number;
            CircleImageView profile_image;
            public ViewAdapter(View itemView) {
                super(itemView);
                mView=itemView;
                imageView=mView.findViewById(R.id.profile_pic);
                linearLayout=mView.findViewById(R.id.view_profile);
                name=mView.findViewById(R.id.name);
                age=mView.findViewById(R.id.age);
                phone_number=mView.findViewById(R.id.phone_number);
                accept=mView.findViewById(R.id.appoint);
                cancel=mView.findViewById(R.id.cancel);

            }


        }
        @NonNull
        @Override
        public  RecycleAdapter.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_rec_list_item2,parent,false);
            return new RecycleAdapter.ViewAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecycleAdapter.ViewAdapter holder, final int position) {


            final Patient_Model memberInfo=memberInfos.get(position);
            holder.name.setText(memberInfo.name);
            holder.age.setText(memberInfo.age);
            holder.phone_number.setText(memberInfo.phone_number);
            holder.name.setSelected(true);
            holder.age.setSelected(true);
            if(memberInfo.image_path.length()>0){


                Picasso.get().load(memberInfo.image_path).placeholder(R.drawable.profile10).into(holder.profile_image);
            }

            holder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

            holder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });


        }

        @Override
        public int getItemCount() {
            return memberInfos.size();
        }



    }
}