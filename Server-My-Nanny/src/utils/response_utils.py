import json

from flask import Response

from src.utils.translator_utils import translate_string


# ------------------------------------------------------------------
# Default responses
# ------------------------------------------------------------------


def _format_response(response, code):
    return Response(
        response=json.dumps(response),
        status=code,
        mimetype='application/json'
    )


def response_success(response):
    return _format_response(response, 200)


def response_failed(response):
    return _format_response(response, 400)


def response_not_authorized(response):
    return _format_response(response, 401)


# ------------------------------------------------------------------
# messages
# ------------------------------------------------------------------

def response_token_invalid(language):
    return response_not_authorized(
        {
            'message': translate_string('token invalid', language)
        }
    )


def response_token_expired(language):
    return response_not_authorized(
        {
            'message': translate_string('token expired', language)
        }
    )


def response_user_already_exists(language):
    return response_failed(
        {
            'message': translate_string('user already exists', language)
        }
    )


def response_user_created(language):
    return response_success(
        {
            'message': translate_string('user created', language)
        }
    )


def response_user_not_found(language):
    return response_failed(
        {
            'message': translate_string('user not found', language)
        }
    )


def response_password_wrong(language):
    return response_failed(
        {
            'message': translate_string('password wrong', language)
        }
    )


def response_token_wrong(language):
    return response_failed(
        {
            'message': translate_string('token invalid', language)
        }
    )


def response_password_updated(language):
    return response_success(
        {
            'message': translate_string('password updated', language)
        }
    )
