from googletrans import Translator


def translate_text(message, language='pt-br'):
    translator = Translator()
    result = translator.translate(message, src='en', dest=language)
    return result.text
