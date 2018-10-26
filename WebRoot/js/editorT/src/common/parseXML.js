 function parseXML(text) {
var xmlDoc;
try //Internet Explorer
{
xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
xmlDoc.async = "false";
xmlDoc.loadXML(text);
} catch (e) {
try //Firefox, Mozilla, Opera, etc.
{
parser = new DOMParser();
xmlDoc = parser.parseFromString(text, "text/xml");
} catch (e) {
alert(e.message);

}
}
return xmlDoc;
}