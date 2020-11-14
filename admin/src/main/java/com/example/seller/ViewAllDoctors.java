package com.example.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ViewAllDoctors extends AppCompatActivity {
    String name, email, phone, user_id;
    ImageButton deletebt, editbt;
    public ArrayList<User> users = new ArrayList<>();
    //public User userToEdit;
    RecyclerView recyclerView;
    TextView numberOfUsers;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_doctors);
        numberOfUsers = findViewById(R.id.view_lekha);
        recyclerView = findViewById(R.id.recycle);
        recycleAdapter = new
                RecycleAdapter(users);
        recyclerView.setAdapter(recycleAdapter);

        get_users();
    }

    public void get_users() {
        Query query = db.collection("doctors");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Map<String, Object> data = documentSnapshot.getData();

                        User user = new User(data.get("user").toString(), data.get("user_name").toString(), data.get("associated_hospital").toString(), data.get("designation").toString(), data.get("phone_no").toString());
                        users.add(user);

                    }
                    numberOfUsers.setText("All Doctors: " + users.size());

                    recycleAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public static class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewAdapter> {
        ArrayList<User> users;

        public RecycleAdapter(ArrayList<User> users) {
            this.users = users;
        }

        public class ViewAdapter extends RecyclerView.ViewHolder {
            TextView name, hospital, phone, designation;
            ImageButton deletebt, editbt;

            public ViewAdapter(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.us_name);
                hospital = itemView.findViewById(R.id.us_hospital);
                phone = itemView.findViewById(R.id.us_phone);
                designation = itemView.findViewById(R.id.us_designation);
                deletebt = itemView.findViewById(R.id.delete_btn);
                editbt = itemView.findViewById(R.id.edit_btn);
            }
        }

        @NonNull
        @Override
        public RecycleAdapter.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.single_doctor_layout, parent, false);
            return new ViewAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecycleAdapter.ViewAdapter holder, int position) {
            User user = users.get(position);

            holder.hospital.setText("Hospital: " + user.hospital);
            holder.designation.setText("Designation: " + user.designation);
            holder.phone.setText("Phone: " + user.phone_number);
            holder.name.setText("Name: " + user.user_name);
            holder.deletebt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteUser(user.user_id);
                    users.remove(user);
                    notifyDataSetChanged();
                }
            });
            holder.editbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //userToEdit=user;
                    Intent intent = new Intent(getApplicationContext(), EditUser.class);
                    intent.putExtra("name", user.user_name);
                    intent.putExtra("hospital", user.hospital);
                    intent.putExtra("id", user.user_id);
                    intent.putExtra("designation", user.designation);
                    intent.putExtra("phone", user.phone_number);
                    //intent.putExtra("userlist",users);
                    startActivity(intent);
                }
            });
        }

        public void deleteUser(String id) {
            db.collection("doctors").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}