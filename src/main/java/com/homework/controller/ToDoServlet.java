package com.homework.controller;

import com.homework.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ToDoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> taskList = (List<Task>) req.getSession().getAttribute("taskList");
        req.setAttribute("taskList", taskList);

        req.getRequestDispatcher("todo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> taskList = (List<Task>) req.getSession().getAttribute("taskList");

        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> mapEntry: params.entrySet()) {
            String paramName = mapEntry.getKey();
            for (Task task: taskList) {
                if (String.valueOf(task.hashCode()).equals(paramName)){
                    taskList.remove(task);
                }
            }
        }
        req.setAttribute("taskList", taskList);

        req.getRequestDispatcher("todo.jsp").forward(req, resp);
    }
}
