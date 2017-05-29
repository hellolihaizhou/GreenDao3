package com.lihaizhou.greendao3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lihaizhou.greendao3.bean.Student;
import com.lihaizhou.greendao3.dao.StudentDao;
import com.lihaizhou.greendao3.utils.GreenDaoUtils;
import com.lihaizhou.greendao3.utils.editUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    StudentDao studentDao;
    private EditText etId;
    private EditText etName;
    private EditText etQueryId;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnQuery;
    private TextView tvQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentDao = GreenDaoUtils.getSingleTon().getmDaoSession().getStudentDao();
        setupView();
    }


    private void setupView() {
        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etQueryId = (EditText) findViewById(R.id.etQueryById);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        tvQuery = (TextView) findViewById(R.id.tvQuery);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnAdd:
                addStudentData();
                break;
            case R.id.btnDelete:
                deleteStudentDataById();
                break;
            case R.id.btnQuery:
                queryStudentDataId();
                break;

        }
    }

    private void addStudentData()
    {
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        if (editUtil.isNotEmpty(id) && editUtil.isNotEmpty(name)) {
            QueryBuilder qb = studentDao.queryBuilder();
            ArrayList<Student> list = (ArrayList<Student>) qb.where(StudentDao.Properties.Id.eq(id)).list();
            if (list.size() > 0) {
                Toast.makeText(MainActivity.this, "已经有该id的学生", Toast.LENGTH_SHORT).show();
            } else {
                studentDao.insert(new Student(Long.valueOf(id), name));
                Toast.makeText(MainActivity.this, "插入学生信息成功", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (editUtil.isEmpty(id) && editUtil.isNotEmpty(name)) {
                Toast.makeText(MainActivity.this, "id为空", Toast.LENGTH_SHORT).show();
            }
            if (editUtil.isEmpty(name) && editUtil.isNotEmpty(id)) {
                Toast.makeText(MainActivity.this, "姓名为空", Toast.LENGTH_SHORT).show();
            }
            if (editUtil.isEmpty(id) && editUtil.isEmpty(name)) {
                Toast.makeText(MainActivity.this, "请填写学生信息", Toast.LENGTH_SHORT).show();
            }

        }
        etId.setText("");
        etName.setText("");
    }

    private void deleteStudentDataById()
    {
        String id = etQueryId.getText().toString();
        if (editUtil.isNotEmpty(id)) {
            QueryBuilder qb = studentDao.queryBuilder();
            ArrayList<Student> list = (ArrayList<Student>) qb.where(StudentDao.Properties.Id.eq(id)).list();
            if (list.size() < 1) {
                Toast.makeText(MainActivity.this, "不存在该学生数据，无法删除", Toast.LENGTH_SHORT).show();
                etId.setText("");
                etName.setText("");
            }
            else
            {
                studentDao.deleteByKey(Long.valueOf(id));
                Toast.makeText(MainActivity.this, "删除学生数据成功", Toast.LENGTH_SHORT).show();
                etId.setText("");
                etName.setText("");
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "id为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void queryStudentDataId()
    {
        String id = etQueryId.getText().toString();
        if (editUtil.isNotEmpty(id)) {
            QueryBuilder qb = studentDao.queryBuilder();
            ArrayList<Student> list = (ArrayList<Student>) qb.where(StudentDao.Properties.Id.eq(id)).list();
            if (list.size() > 0) {
                String text = "查询结果为：";
                for (Student Student : list) {
                    text = text + Student.getName();
                }
                tvQuery.setText(text);
            } else {
                tvQuery.setText("");
                Toast.makeText(MainActivity.this, "不存在该学生数据", Toast.LENGTH_SHORT).show();
            }
            etId.setText("");
            etName.setText("");
        } else {
            Toast.makeText(MainActivity.this, "id为空", Toast.LENGTH_SHORT).show();
        }
    }
}