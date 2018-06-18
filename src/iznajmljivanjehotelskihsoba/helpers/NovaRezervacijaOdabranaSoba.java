/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iznajmljivanjehotelskihsoba.helpers;

import iznajmljivanjehotelskihsoba.models.Soba;

/**
 *
 * @author owner
 */
public class NovaRezervacijaOdabranaSoba {
    public static Soba soba;
    public static Soba getSoba(){ return soba; }
    public static void setSoba(Soba s){ soba = s; }
}
