package it.unisa.tophw.server.controller.product;

import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.beans.UserBean;
import it.unisa.tophw.server.model.management.BrandDAO;
import it.unisa.tophw.server.model.management.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletFindProductByFilters")
public class ServletFindProductByFilters extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome=null;
        Integer prezzoMin=null;
        Integer prezzoMax=null;
        Integer id_marca=null;
        String nomeMarca=null;


        nome = (request.getParameter("nome")!=null && request.getParameter("nome").length()>0) ? request.getParameter("nome") : null;
        prezzoMin = (request.getParameter("prezzo_minimo")!=null && request.getParameter("prezzo_minimo").length()>0) ? Integer.parseInt(request.getParameter("prezzo_minimo").toString()) : null;
        prezzoMax = (request.getParameter("prezzo_massimo")!=null && request.getParameter("prezzo_massimo").length()>0) ? Integer.parseInt(request.getParameter("prezzo_massimo").toString()) : null;
        id_marca = (request.getParameter("id_marca")!=null && request.getParameter("id_marca").length()>0) ? Integer.parseInt(request.getParameter("id_marca").toString()) : null;
        nomeMarca = (request.getParameter("nome_marca")!=null && request.getParameter("nome_marca").length()>0) ? request.getParameter("nome_marca") : null;

        ProductBean productBean = new ProductBean();
        ProductDAO productDAO = new ProductDAO();

        if(nome!=null)
            productBean.setNome(nome);
        else
            productBean.setNome(null);

        //Il prezzo min lo imposto in prezzo, nel dao, se c'è il val -1 nn prendo in considerazione il filtro
        if(prezzoMin!=null)
            productBean.setPrezzo(prezzoMin);
        else
            productBean.setPrezzo(-1);
        //Il prezzo max lo imposto nella quantita, nel dao, se c'è il val -1 nn prendo in considerazione il filtro
        if(prezzoMax!=null)
            productBean.setQuantita(prezzoMax);
        else
            productBean.setQuantita(-1);

        productBean.setId_marca(-1);
        if(id_marca!=null || nomeMarca!=null){
            BrandDAO brandDAO=new BrandDAO();
            BrandBean brandBean = new BrandBean();


            if(id_marca!=null){
                brandBean=brandDAO.doRetriveByKey(id_marca);
            }
            else
            if(nomeMarca!=null){
                brandBean=brandDAO.doRetriveBynome(nomeMarca);
            }


            if(brandBean!=null)
                productBean.setId_marca(brandBean.getIdMarca());
            else
                productBean.setId_marca(-1);


        }

        ArrayList<ProductBean> products = new ArrayList<ProductBean>();
        if(products!=null){
            products = productDAO.doRetriveByFilters(productBean,null);
        }
        System.out.println(products.size()+"<--- size");

        request.setAttribute("prodotti", products);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {






    }
}
