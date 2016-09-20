package com.homework.controller;

import com.homework.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskName = req.getParameter("name");
        String taskCategory = req.getParameter("category");

        Task newTask = new Task(taskName, taskCategory);

        List<Task> taskList = new ArrayList<>();

        Optional<List<Task>> taskListAttribute = Optional.ofNullable((List<Task>) req.getSession().getAttribute("taskList"));
        if (taskListAttribute.isPresent())
            taskList = taskListAttribute.get();

        taskList.add(newTask);
        req.getSession().setAttribute("taskList", taskList);

        req.getRequestDispatcher("todo.jsp").forward(req, resp);

    }
}
