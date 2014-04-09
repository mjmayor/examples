@RequestMapping(value = "/directos/multisignals/{idMultisignal}/destinies/{idDestiny}/delete/", method = RequestMethod.GET)
public ModelAndView deleteMultisignalDestinies(@PathVariable long idMultisignal, @PathVariable long idDestiny,RedirectAttributes redirectAttributes)  {
	log.info("DestiniesController - deleteMultisignalDestinies - INICIO");
	
	try {
		multisignalDestinyService.delete(idMultisignal,idDestiny);
	} catch (Exception e) {
		redirectAttributes.addFlashAttribute("exito", "ko");
		log.error("DestiniesController - deleteMultisignalDestinies",e);
		log.info("DestiniesController - deleteMultisignalDestinies - FIN");
		return new ModelAndView("redirect:/directos/multisignals/"+idMultisignal+"/destinies");
	}
	
	redirectAttributes.addFlashAttribute("exito", "ok");
	log.info("DestiniesController - deleteMultisignalDestinies - FIN");
	return new ModelAndView("redirect:/directos/multisignals/"+idMultisignal+"/destinies");
}
