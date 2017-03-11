/**
 * Created by Mel on 19/02/2017.
 */

// TEST FORMULAIRE ADHERENT

function verifNom(champ)
{
    if(champ.value.length < 2 || champ.value.length > 25)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifPrenom(champ)
{
    if(champ.value.length < 2 || champ.value.length > 10)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifVille(champ)
{
    if(champ.value.length < 3 || champ.value.length > 10)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifFormAdherent(f)
{
    var nomOk = verifNom(f.nom);
    var prenomOk = verifPrenom(f.prenom);
    var villeOk = verifVille(f.ville)

    if(nomOk & prenomOk & villeOk){
        return true;}
    else
    {
        $("#erreur").show("slow");
        $(".close").click(function() {
            $(".alert").hide("slow");
        });
        return false;
    }
}

// TEST FORMULAIRE OEUVRE

function verifTitre(champ)
{
    if(champ.value.length < 3 || champ.value.length > 25)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifPrix(champ)
{
    if(champ.value.length < 0 && (parseFloat(value) == parseInt(value)))
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifFormOeuvre(f)
{
    var titreOk = verifTitre(f.titre);
    var prixOk = verifPrix(f.prix);

    if(titreOk & prixOk){
        return true;}
    else
    {
        if (!titreOk){
            $("#erreur").show("slow");
            $(".close").click(function() {
                $(".alert").hide("slow");
            });
        }

        return false;
    }
}

//TEST FORMULAIRE OWNER

function verifFormOwner(f)
{
    var nomOk = verifNom(f.nom);
    console.log(nomOk);
    var prenomOk = verifPrenom(f.prenom);

    if(nomOk & prenomOk){
        return true;}
    else
    {
        $("#erreur").show("slow");
        $(".close").click(function() {
            $(".alert").hide("slow");
        });
        return false;
    }
}


//AUTRES

function surligne(champ, erreur)
{
    if(erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}