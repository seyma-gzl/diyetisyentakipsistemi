package Utility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author seyma
 */
import Entity.Kisi;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static void setUser(Kisi user) {
        HttpSession session = getSession();
        if (user != null) {
            session.setAttribute("user", user);
            setUserId(user);
            isAdmin();
        }
    }

    public static Kisi getUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (Kisi) session.getAttribute("user");
        } else {
            return null;
        }
    }

    public static Boolean isAdmin() {
        HttpSession session = getSession();
        if (SessionUtils.getUser() != null) {
            if (SessionUtils.getUser().getYetki() != null) {
                if (SessionUtils.getUser().getYetki().isAdmin()) {
                    session.setAttribute("isAdmin", 1);
                    return true;
                }
            }
        }
        session.setAttribute("isAdmin", 0);
        return false;
    }

    public static void setUserId(Kisi user) {
        HttpSession session = getSession();
        if (user != null) {
            session.setAttribute("userid", user.getKullaniciAdi());
        }
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }

    public static Boolean isLoggedin() {
        HttpSession session = getSession();
        if (session != null) {
            if (SessionUtils.getUser() != null) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isHasta() {
        HttpSession session = getSession();
        if (SessionUtils.getUser() != null) {
            if (SessionUtils.getUser().getYetki().isHasta()) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isDoktor() {
        HttpSession session = getSession();
        if (SessionUtils.getUser() != null) {
            if (SessionUtils.getUser().getYetki().isDoktor()) {
                return true;
            }
        }

        return false;
    }

}
