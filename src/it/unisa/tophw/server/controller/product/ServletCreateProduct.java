package it.unisa.tophw.server.controller.product;


import it.unisa.tophw.server.model.beans.BrandBean;
import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import it.unisa.tophw.server.model.management.BrandDAO;
import it.unisa.tophw.server.model.management.CatalogDAO;
import it.unisa.tophw.server.model.management.ProductDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.List;


@MultipartConfig
@WebServlet(name = "ServletCreateProduct")
public class ServletCreateProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean error = false;
        String msgOutput = "";
        String nome = request.getParameter("Nome_Prodotto") != null && request.getParameter("Nome_Prodotto").length() > 0 ? request.getParameter("Nome_Prodotto").trim() : "";
        String descrizione_b = request.getParameter("Descrizione_Breve") != null && request.getParameter("Descrizione_Breve").length() > 0 ? request.getParameter("Descrizione_Breve").trim() : "";
        String descrizione = request.getParameter("Descrizione") != null && request.getParameter("Descrizione").length() > 0 ? request.getParameter("Descrizione").trim() : "";
        Double prezzo = Double.parseDouble(request.getParameter("Prezzo") != null && request.getParameter("Prezzo").length() > 0 ? request.getParameter("Prezzo") : "0");
        String stringId_marca = request.getParameter("selectMarca") != null && request.getParameter("selectMarca").length() > 0 ? request.getParameter("selectMarca") : "";
        String stringId_catalogo = request.getParameter("selectCatalog") != null && request.getParameter("selectCatalog").length() > 0 ? request.getParameter("selectCatalog") : "";

        int perc_sconto = Integer.parseInt(request.getParameter("perc_sconto") != null && request.getParameter("perc_sconto").length() > 0 ? request.getParameter("perc_sconto") : "0");

        int quantita = Integer.parseInt(request.getParameter("quantita") != null && request.getParameter("quantita").length() > 0 ? request.getParameter("quantita") : "0");

        int id_marca = 0;
        int id_catalogo = 0;
        if (stringId_catalogo != null && stringId_catalogo.length() > 0)
            id_catalogo = Integer.parseInt(stringId_catalogo);
        if (stringId_marca != null && stringId_marca.length() > 0)
            id_marca = Integer.parseInt(stringId_marca);

        ProductBean prodotto = new ProductBean();

        if (nome != null && nome.length() > 0) {

            prodotto.setNome(nome);
            prodotto.setDescrizione_breve(descrizione_b);
            prodotto.setDescrizione_estesa(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);
            prodotto.setPerc_sconto(perc_sconto);

            BrandBean brand = new BrandBean();
            BrandDAO brandDAO = new BrandDAO();

            if (id_marca > 0) {
                brand = brandDAO.doRetriveByKey(id_marca);
                if (brand != null && brand.getIdMarca() > 0)
                    prodotto.setId_marca(brand.getIdMarca());
            }


            CatalogBean catalogBean = new CatalogBean();
            CatalogDAO catalogDAO = new CatalogDAO();

            if (id_catalogo > 0) {
                catalogBean = catalogDAO.doRetriveByKey(id_catalogo);
                if (catalogBean != null && catalogBean.getId_catalogo() > 0)
                    prodotto.setId_catalogo(catalogBean.getId_catalogo());

            }

            //Immagini
            Part part = null;
            String name_img = null;
        System.out.println("Qui");
            if (ServletFileUpload.isMultipartContent(request)) {

                try {
                    List<FileItem> multiparts = new ServletFileUpload(

                            new DiskFileItemFactory()).parseRequest(request);


                    for (FileItem item : multiparts) {

                        if (!item.isFormField()) {

                            String name = new File(item.getName()).getName();

                            item.write(new File(getServletContext().getRealPath("/images") + File.separator + name));

                        }

                    }


            /*
            if(request.getPart("img1").getSize()>0){
                Part part1 = request.getPart("img1");
                name_img= extractFileName(part1);
                prodotto.setPathImg1(name_img);
                System.out.println(getServletContext().getRealPath("/images"));
                System.out.println(getServletContext().getContextPath()+"/images");
                part1.write(getServletContext().getRealPath("/images")+"/"+name_img);
            }
            if(request.getPart("img2").getSize()>0){
                part = request.getPart("img2");
                name_img= extractFileName(part);
                prodotto.setPathImg2(name_img);
                part.write(getServletContext().getRealPath("/images")+"/"+name_img);
            }
            if(request.getPart("img3").getSize()>0){
                part = request.getPart("img3");
                name_img= extractFileName(part);
                prodotto.setPathImg3(name_img);
                part.write(getServletContext().getRealPath("/images")+"/"+name_img);
            }
*/

                    /*Catch per le immagini*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ProductDAO productDAO = new ProductDAO();
            if (productDAO.doSave(prodotto)) {
                msgOutput = "ok";
            } else {
                msgOutput = "Errore durante l'inserimento del prodotto";
            }
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminPanel.jsp?msgOutput=" + msgOutput + "");
                dispatcher.forward(request, response);

        }
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
