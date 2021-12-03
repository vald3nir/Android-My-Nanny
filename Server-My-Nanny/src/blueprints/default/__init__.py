import json

from flask import Response

from src.utils.translator_utils import translate_text


class UseCase:

    def _format_response(self, response, code):
        return Response(
            response=json.dumps(response),
            status=code,
            mimetype='application/json'
        )

    def translate_string(self, string, language):
        return translate_text(string, language)

    def response_success(self, response):
        return self._format_response(response, 200)

    def response_failed(self, response):
        return self._format_response(response, 400)
