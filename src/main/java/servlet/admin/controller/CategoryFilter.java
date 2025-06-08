package servlet.admin.controller;

import servlet.constants.SearchConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/category/search")
public class CategoryFilter extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CategoryFilter() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int size = SearchConstants.CATEGORY_DEFAULT_SIZE; //default
        int page = SearchConstants.DEFAULT_PAGE; //default
        String dir = SearchConstants.DEFAULT_DIR; //default
        String sortBy = "category_id";
        String keyWord = null;
        int isActive = -1;

        StringBuilder urlSearch = new StringBuilder();
        urlSearch.append("/admin/categories-view?");

        if(request.getParameter("size") != null) {
            urlSearch.append("size=");
            size = Integer.parseInt(request.getParameter("size"));
            urlSearch.append(size);
            urlSearch.append("&");
        } else {
            urlSearch.append("size="+size);
            urlSearch.append("&");
        }

        if(request.getParameter("page") != null) {
            urlSearch.append("page=");
            page = Integer.parseInt(request.getParameter("page"));
            urlSearch.append(page);
            urlSearch.append("&");
        } else {
            urlSearch.append("page=1");
            urlSearch.append("&");
        }

        if(request.getParameter("dir") != null) {
            urlSearch.append("dir=");
            dir = request.getParameter("dir");
            urlSearch.append(dir);
            urlSearch.append("&");
        } else {
            urlSearch.append("dir=ASC");
            urlSearch.append("&");
        }

        if(request.getParameter("sortBy") != null) {
            urlSearch.append("sortBy=");
            sortBy = request.getParameter("sortBy");
            urlSearch.append(sortBy);
            urlSearch.append("&");
        } else {
            urlSearch.append("sortBy=category_id");
            urlSearch.append("&");
        }

        if(request.getParameter("keyWord") != null) {
            urlSearch.append("keyWord=");
            keyWord = request.getParameter("keyWord");
            urlSearch.append(keyWord);
            urlSearch.append("&");

        }

        if(request.getParameter("active") != null) {
            urlSearch.append("active=");
            isActive = Integer.parseInt(request.getParameter("active"));
            urlSearch.append(isActive);
        }
        request.setAttribute("size", size);
        request.setAttribute("page", page);
        request.setAttribute("dir", dir);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("active", isActive);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("urlSearch", urlSearch.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
