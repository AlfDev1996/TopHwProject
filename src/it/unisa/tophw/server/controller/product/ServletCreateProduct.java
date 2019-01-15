package it.unisa.tophw.server.controller.product;

import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.management.BrandDAO;
import it.unisa.tophw.server.model.management.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "ServletCreateProduct")
public class ServletCreateProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("Nome_Prodotto").trim();
        String descrizione_b = request.getParameter("Descrizione_Breve").trim();
        String descrizione = request.getParameter("Descrizione").trim();
        Double prezzo = Double.parseDouble(request.getParameter("Prezzo"));
        String marca = request.getParameter("marca");
        int quantita=Integer.parseInt(request.getParameter("quantita"));


        ProductBean prodotto = new ProductBean();

        prodotto.setNome(nome);
        prodotto.setDescrizione_breve(descrizione_b);
        prodotto.setDescrizione_estesa(descrizione);
        prodotto.setPrezzo(prezzo);
        prodotto.setQuantita(quantita);

        BrandBean brand = null;
        BrandDAO brandDAO = new BrandDAO();

        brand = brandDAO.doRetriveBynome(marca);
        if(brand!=null)
            prodotto.setMarca(brand);

        //Immagini
        Part part = null;
        String name_img= null;

        if(request.getPart("img1").getSize()>0){
            part = request.getPart("img1");
            name_img= extractFileName(part);
            prodotto.setPathImg1(name_img);
            part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_img);
        }
        if(request.getPart("img2").getSize()>0){
            part = request.getPart("img2");
            name_img= extractFileName(part);
            prodotto.setPathImg2(name_img);
            part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_img);
        }
        if(request.getPart("img3").getSize()>0){
            part = request.getPart("img3");
            name_img= extractFileName(part);
            prodotto.setPathImg3(name_img);
            part.write(getServletContext().getRealPath("/themes/images/prodotti")+"/"+name_img);
        }

        String msgOutput="";
        ProductDAO productDAO = new ProductDAO();
        if(productDAO.doSave(prodotto)){
            msgOutput="ok";
        }else{
            msgOutput="Errore durante l'inserimento del prodotto";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AggiungiArticolo.jsp?msgOutput="+msgOutput+"");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
